package com.example.moviewatcher.Player

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import com.example.moviewatcher.R
import com.example.moviewatcher.Utils.Common
import com.example.moviewatcher.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.Util

class PlayerActivity : AppCompatActivity(), Player.Listener {


    private lateinit var player: ExoPlayer
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L
    private lateinit var progressBar: ProgressBar

    private var link: String? = null
    private lateinit var binding: ActivityPlayerBinding
    private val videoNotificationManager = VideoNotificationManager()


    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPlayerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityPlayerBinding>(
            this, R.layout.activity_player
        )
        setContentView(binding.root)
        progressBar = binding.progressBar
        //get the data from recycler view
        fillInActivity()
        // exoPlayer initialization and data filling
        initPlayer()

        // check for phone state
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt("mediaItem") != 0) {
                val restoredMediaItem = savedInstanceState.getInt("mediaItem")
                val seekTime = savedInstanceState.getLong("SeekBar")
                player.seekTo(restoredMediaItem, seekTime)
                player.play()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // hideSystemUi()
        if ((Util.SDK_INT <= 23)) {
            initPlayer()
        }
    }

    //hiding upper interface
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
        releasePlayer()

    }

    override fun onStop() {
        super.onStop()
        releasePlayer()

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun fillInActivity() {
        val videoData = intent
        link = videoData.getStringExtra("link")
    }

    private fun initPlayer() {
        val trackSelector = DefaultTrackSelector(this).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }
        player = ExoPlayer.Builder(this)
            .setTrackSelector(trackSelector)
            .build()
        binding.videoView.player = player
        player.addListener(this)
        addItems()
        //create notification control
        videoNotificationManager.createNotification(player, this)
        player.prepare()
    }

    private fun addItems() {
        val mediaItems: MutableList<MediaItem> = ArrayList()

        for (i in 0 until Common.videoList.size) {
            if (link == Common.videoList[i]) {
                currentItem = i
            }
            mediaItems.add(MediaItem.fromUri(Common.videoList[i]))
        }
        player.setMediaItems(mediaItems)
        if (currentItem == 0) {
            playbackPosition = Common.videoList.size.toLong()
        } else {
            playbackPosition = (currentItem - 1).toLong()
        }
        player.playWhenReady = playWhenReady
        player.seekTo(currentItem, playbackPosition)

    }


    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        // player = null
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                progressBar.visibility = View.VISIBLE
            }
            Player.STATE_READY -> {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }

    override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {
        super.onMediaMetadataChanged(mediaMetadata)
        videoNotificationManager.tittle = Common.titleList[currentItem]
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("SeekBar", player.currentPosition)
        outState.putInt("mediaItem", player.currentMediaItemIndex)
    }


}