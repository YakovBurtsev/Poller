package com.yakovburtsev.poller

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import androidx.work.ListenableWorker
import com.yakovburtsev.poller.Util.scheduleJob
import java.net.HttpURLConnection
import java.net.URL

private const val TAG = "PollWorker"

class PollJobService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        poll()
        scheduleJob(applicationContext) // reschedule the job
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    private fun poll(): ListenableWorker.Result {
        val urlString = "https://finotifier.herokuapp.com/"
        val url = URL(urlString)
        var urlConnection: HttpURLConnection? = null
        try {
            urlConnection = url.openConnection() as HttpURLConnection?
            Log.i(TAG, "Polled url: $urlString, status: ${urlConnection?.responseCode}")
        } finally {
            urlConnection?.disconnect()
        }
        return ListenableWorker.Result.success()
    }
}