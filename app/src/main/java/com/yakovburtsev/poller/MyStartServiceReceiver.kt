package com.yakovburtsev.poller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.yakovburtsev.poller.Util.scheduleJob


class MyStartServiceReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            scheduleJob(context)
        }
    }
}