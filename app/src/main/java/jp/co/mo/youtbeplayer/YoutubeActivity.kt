package jp.co.mo.youtbeplayer

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "cnn-z4U_S50"
const val YOUTUBE_PLAY_LIST = "PLAKw47hquUsIWArq-QR2v_wR-JZIjASWv"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private val TAG = YoutubeActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube)
//        val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)
        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

        val youtubeView = YouTubePlayerView(this)
        youtubeView.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(youtubeView)

        youtubeView.initialize(getString(R.string.google_api_key), this)

    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, wasRestored: Boolean) {
        Log.e(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
        Log.e(TAG, "onInitializationSuccess: youtubePlayer is ${youtubePlayer?.javaClass}")
        Toast.makeText(this, "initialized youtube player successfully", Toast.LENGTH_LONG).show()
        if(!wasRestored) {
            youtubePlayer?.cueVideo(YOUTUBE_VIDEO_ID)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        val REQUEST_CODE = 0

        if (result?.isUserRecoverableError == true) {
            result.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            val errorMessage = "There was an error initializing the Youttube Player ($result)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

}
