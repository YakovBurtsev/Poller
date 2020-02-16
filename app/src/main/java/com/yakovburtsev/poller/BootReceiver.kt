package com.yakovburtsev.poller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {

            val workRequest = PeriodicWorkRequest
                .Builder(PollWorker::class.java, 20L, TimeUnit.MINUTES)
                .build()

            WorkManager.getInstance()
                .enqueue(workRequest)
        }
    }
}