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
import com.mechjobradar.app.model.MechJobPost

class JobScanWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val jobs = JobRepository.getProductionMechanicalJobs()
        
        // Push notification for freshly opened vacancies posted within 6 hours
        val urgentEarlyJobs = jobs.filter { it.postedHoursAgo <= 6 }

        if (urgentEarlyJobs.isNotEmpty()) {
            val topJob = urgentEarlyJobs.first()
            sendUrgentNotification(topJob)
        }

        return Result.success()
    }

    private fun sendUrgentNotification(job: MechJobPost) {
        val channelId = "south_mech_alerts_channel"
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "South India Mechanical Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Instant alerts for OEM & Tier 1 Mechanical Engineering jobs"
            }
            manager.createNotificationChannel(channel)
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job.applyUrl)).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("⚡ [Early Apply] ${job.company} (${job.location})")
            .setContentText("${job.title} • Req: ${job.minExpYears}-${job.maxExpYears} yrs exp")
            .setStyle(NotificationCompat.BigTextStyle().bigText("${job.title} at ${job.company} [${job.tier.label}]. Posted ${job.postedHoursAgo}h ago in ${job.location}. Tap to apply directly on official portal."))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        manager.notify(job.id.hashCode(), notification)
    }
}
