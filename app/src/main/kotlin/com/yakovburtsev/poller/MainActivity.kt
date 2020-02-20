package com.yakovburtsev.poller

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yakovburtsev.poller.Util.scheduleJob


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        scheduleJob(this)
    }
}
