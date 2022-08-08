package com.example.moviewatcher.Player

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import com.example.moviewatcher.R
import com.example.moviewatcher.Utils.Common
import com.example.moviewatcher.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import java.lang.Exception

class PlayerActivity : AppCompatActivity() {


    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

    private var link: String? = null
    private lateinit var binding: ActivityPlayerBinding

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPlayerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityPlayerBinding>(
            this, R.layout.activity_player
        )
        setContentView(binding.root)
        fillInActivity()        

    }

    override fun onResume() {
        super.onResume()
       // hideSystemUi()
        if (player == null) {
            initPlayer()
        }
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, viewBinding.videoView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            releasePlayer()
        } catch (e: Exception) {
        }
    }

    override fun onStop() {
        super.onStop()
        try {
            releasePlayer()
        } catch (e: Exception) {
        }
    }

    private fun fillInActivity() {
        val videoData = intent
        val title = videoData.getStringExtra("title")
        link = videoData.getStringExtra("link")
        binding.title.text = title.toString()
    }

    private fun initPlayer() {

        val trackSelector = DefaultTrackSelector(this).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }

        player = ExoPlayer.Builder(this)
            .setTrackSelector(trackSelector)
            .build().also { exoPlayer ->
                var videoNum=0
                binding.videoView.player = exoPlayer
                val mediaItem = MediaItem.fromUri(link.toString())
                exoPlayer.setMediaItem(mediaItem)
                for (i in 0 until Common.videoList.size) {
                    if (link==Common.videoList[i]) {
                        videoNum=i
                    }
                }
                for (i in videoNum+1 until Common.videoList.size) {
                    exoPlayer.addMediaItem(MediaItem.fromUri(Common.videoList[i]))
                }
                for (i in 0 until videoNum-1) {
                    exoPlayer.addMediaItem(MediaItem.fromUri(Common.videoList[i]))
                }
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.prepare()
            }

    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }
}