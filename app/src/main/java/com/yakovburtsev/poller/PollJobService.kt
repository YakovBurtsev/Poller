package com.yakovburtsev.poller

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import com.yakovburtsev.poller.Util.scheduleJob


class PollJobService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        val intent = Intent(applicationContext, PollService::class.java)
        applicationContext.startService(intent)
        scheduleJob(applicationContext) // reschedule the job
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

}