package com.yakovburtsev.poller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workRequest = PeriodicWorkRequest
            .Builder(PollWorker::class.java, 10L, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance()
            .enqueue(workRequest)
    }
}
