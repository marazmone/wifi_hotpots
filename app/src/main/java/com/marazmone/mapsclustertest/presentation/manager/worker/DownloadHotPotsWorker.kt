package com.marazmone.mapsclustertest.presentation.manager.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.marazmone.mapsclustertest.R.drawable
import com.marazmone.mapsclustertest.presentation.manager.notification.PendingIntentConstructor
import com.marazmone.mapsclustertest.presentation.usecase.LoadHotpotsFromAssetToDBUseCase

class DownloadHotPotsWorker(
    appContext: Context,
    params: WorkerParameters,
    private val loadHotpotsFromAssetToDBUseCase: LoadHotpotsFromAssetToDBUseCase,
) : CoroutineWorker(appContext, params) {

    private val notificationManager = appContext
        .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override suspend fun doWork(): Result {
        setForeground(createForegroundInfo())
        loadHotpotsFromAssetToDBUseCase.execute()
        return Result.success()
    }

    private fun createForegroundInfo(): ForegroundInfo {
        val title = "WiFi hotpots"
        val notificationId = 111
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createChannel()
        val notification = NotificationCompat.Builder(
            applicationContext,
            "channel_for_download_hotpots"
        )
            .setContentTitle(title)
            .setTicker(title)
            .setContentText("Downloading...")
            .setContentIntent(PendingIntentConstructor.buildPendingIntent(applicationContext))
            .setSmallIcon(drawable.ic_notification_wifi)
            .setOngoing(true)
            .build()

        notificationManager.notify(notificationId, notification)
        return ForegroundInfo(notificationId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val name = "Download hotpots"
        val descriptionText = "Download hotpots"
        val importance = NotificationCompat.PRIORITY_HIGH
        val channel = NotificationChannel(
            "channel_for_download_hotpots",
            name,
            importance
        ).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)
    }
}