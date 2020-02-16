package com.yakovburtsev.poller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.yakovburtsev.poller.Util.scheduleJob


class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            notifyUpdate(context)

            scheduleJob(context)
        }
    }

    private fun notifyUpdate(context: Context) {
        val notifierIntent = Intent(context, MessageService::class.java)
        notifierIntent.putExtra(EXTRA_MESSAGE, "boot")
        context.startService(notifierIntent)
    }
}