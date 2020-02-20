package com.yakovburtsev.poller

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat


const val EXTRA_MESSAGE = "message"
const val NOTIFICATION_ID = 5453

class MessageService : IntentService("MessageService") {

    private val channelId = "CHANNEL_001"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onHandleIntent(intent: Intent) {
        val text = intent.getStringExtra(EXTRA_MESSAGE)
        showText(text)
    }

    private fun showText(text: String) {
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(getString(R.string.button_text))
            .setContentText(text)
            .setVibrate(longArrayOf(0, 1000))
            .setAutoCancel(true)
        val actionIntent = Intent(this, MainActivity::class.java)
        val actionPendingIntent = PendingIntent.getActivity(
            this,
            0,
            actionIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(actionPendingIntent)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.channel_name)
            val description = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}