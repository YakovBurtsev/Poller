package com.yakovburtsev.poller

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat


val EXTRA_MESSAGE = "message"
val NOTIFICATION_ID = 5453

class MessageService : IntentService("MessageService") {
    private val CHANNEL_ID = "CHANNEL_001"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onHandleIntent(intent: Intent) {
        val text = intent.getStringExtra(EXTRA_MESSAGE)
        showText(text)
    }

    private fun showText(text: String) { //Create a notification builder
//        Log.v("DelayedMessageService", "Create a notification builder")
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground) //todo sym_def_
            .setContentTitle(getString(R.string.question))
            .setContentText(text)
            .setVibrate(longArrayOf(0, 1000))
            .setAutoCancel(true)
        //Create an action
//        Log.v("DelayedMessageService", "Create an action")
        val actionIntent = Intent(this, MainActivity::class.java)
        val actionPendingIntent = PendingIntent.getActivity(
            this,
            0,
            actionIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(actionPendingIntent)
        //Issue the notification
//        Log.v("DelayedMessageService", "Issue the notification")
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    private fun createNotificationChannel() { // Create the NotificationChannel, but only on API 26+ because
// the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.channel_name)
            val description = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
// or other notification behaviors after this
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}