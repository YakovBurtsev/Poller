package com.yakovburtsev.poller

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.net.HttpURLConnection
import java.net.URL


private const val TAG = "PollWorker"

class PollWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val urlString = "https://finotifier.herokuapp.com/"
        val url = URL(urlString)
        var urlConnection: HttpURLConnection? = null
        try {
            urlConnection = url.openConnection() as HttpURLConnection?
            Log.i(TAG, "Polled url: $urlString, status: ${urlConnection?.responseCode}")
        } finally {
            urlConnection?.disconnect()
        }
        return Result.success()
    }
}