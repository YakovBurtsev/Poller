package com.yakovburtsev.poller

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.apache.http.HttpResponse
import org.apache.http.StatusLine
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients


private const val TAG = "PollWorker"

class PollWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val httpclient: HttpClient = HttpClients.createDefault()
        val response: HttpResponse = httpclient.execute(
            HttpGet("http://finotifier.herokuapp.com/")
        )
        val statusLine: StatusLine = response.statusLine
        Log.i(TAG, "Polled status: ${statusLine.statusCode}")
        return Result.success()
    }
}