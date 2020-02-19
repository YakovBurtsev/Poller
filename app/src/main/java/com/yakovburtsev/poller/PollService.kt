package com.yakovburtsev.poller

import android.app.IntentService
import android.content.Intent
import java.net.HttpURLConnection
import java.net.URL


class PollService : IntentService("PollService") {

    override fun onHandleIntent(intent: Intent?) {
        val url = URL("https://finotifier.herokuapp.com/")
        var urlConnection: HttpURLConnection? = null
        try {
            urlConnection = url.openConnection() as HttpURLConnection?
            //todo
            println(urlConnection?.responseCode)
            notifyUpdate()
        } finally {
            urlConnection?.disconnect()
        }
    }

    private fun notifyUpdate() {
        val notifierIntent = Intent(this, MessageService::class.java)
        notifierIntent.putExtra(EXTRA_MESSAGE, resources.getString(R.string.response))
        startService(notifierIntent)
    }

}