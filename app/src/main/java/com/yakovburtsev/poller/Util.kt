package com.yakovburtsev.poller

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context


object Util {
    fun scheduleJob(context: Context) {
        val serviceComponent = ComponentName(context, PollJobService::class.java)
        val builder = JobInfo.Builder(0, serviceComponent)
        builder.setMinimumLatency(15 * 60 * 1000.toLong()) // wait at least
        builder.setOverrideDeadline(20 * 60 * 1000.toLong()) // maximum delay
        val jobScheduler = context.getSystemService(JobScheduler::class.java)
        jobScheduler.schedule(builder.build())
    }
}