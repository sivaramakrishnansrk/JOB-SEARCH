package com.mechjobradar.app.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mechjobradar.app.data.JobRepository
import com.mechjobradar.app.data.NotificationStore
import com.mechjobradar.app.model.MechJobPost
import com.mechjobradar.app.model.NotificationItem

class JobScanWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val jobs = JobRepository.getTamilNaduManufacturingJobs()
        val urgentEarlyJobs = jobs.filter { it.postedHoursAgo <= 6 }

        urgentEarlyJobs.forEach { job ->
            // Save to Missed Notifications history tab
            NotificationStore.addNotification(
                NotificationItem(
                    id = job.id,
                    jobTitle = job.title,
                    company = job.company,
                    location = job.plantLocation,
                    applyUrl = job.applyUrl,
                    isEarlyAlert = true
                )
            )
            sendUrgentNotification(job)
        }

        return Result.success()
    }

    private fun sendUrgentNotification(job: MechJobPost) {
        val channelId = "tn_mech_radar_alerts"
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Tamil Nadu OEM/Tier-1 Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Direct alerts for fresh manufacturing vacancies"
            }
            manager.createNotificationChannel(channel)
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job.applyUrl)).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            applicationContext, job.id.hashCode(), intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("⚡ Early Apply: ${job.company}")
            .setContentText("${job.title} (${job.plantLocation})")
            .setStyle(NotificationCompat.BigTextStyle().bigText("${job.title} at ${job.company} [${job.tier.label}]. Location: ${job.plantLocation}. Tap to apply directly on official portal."))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        manager.notify(job.id.hashCode(), notification)
    }
}
