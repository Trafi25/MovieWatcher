package com.example.moviewatcher.Player

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import com.example.moviewatcher.R
import com.example.moviewatcher.Utils.Constants
import com.example.moviewatcher.Utils.Constants.NOTIFICATION_CHANNEL_ID
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager


class VideoNotificationManager {

    var tittle: String = "video"
    lateinit var playerNotificationManager: PlayerNotificationManager
    private val mediaDescriptionAdapter: PlayerNotificationManager.MediaDescriptionAdapter =
        object : PlayerNotificationManager.MediaDescriptionAdapter {
            override fun getCurrentSubText(player: Player): String? {
                return null
            }

            override fun getCurrentContentTitle(player: Player): String {
                return tittle
            }

            override fun createCurrentContentIntent(player: Player): PendingIntent? {
                return null
            }

            override fun getCurrentContentText(player: Player): String? {
                return null
            }

            override fun getCurrentLargeIcon(
                player: Player,
                callback: PlayerNotificationManager.BitmapCallback
            ): Bitmap? {
                return null
            }
        }

    fun createNotification(playerNot: ExoPlayer, context: Context) {


        playerNotificationManager = PlayerNotificationManager.Builder(
            context, Constants.NOTIFICATION_ID, NOTIFICATION_CHANNEL_ID
        )
            .setSmallIconResourceId(R.drawable.ic_play_arrow_24)
            .setMediaDescriptionAdapter(mediaDescriptionAdapter)
            .setNotificationListener(object : PlayerNotificationManager.NotificationListener {
                override fun onNotificationPosted(
                    notificationId: Int,
                    notification: Notification,
                    ongoing: Boolean
                ) {
                }

                override fun onNotificationCancelled(
                    notificationId: Int,
                    dismissedByUser: Boolean
                ) {
                }
            }).build()
        playerNotificationManager.setPlayer(playerNot)

    }


}