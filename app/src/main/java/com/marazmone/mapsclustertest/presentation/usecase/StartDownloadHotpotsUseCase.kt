package com.marazmone.mapsclustertest.presentation.usecase

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.marazmone.mapsclustertest.presentation.manager.DownloadHotPotsWorker
import com.marazmone.mapsclustertest.domain.repository.AppMarkerRepository

private const val TAG = "StartDownloadHotpotsUseCase"

class StartDownloadHotpotsUseCase(
    private val workManager: WorkManager,
    private val repository: AppMarkerRepository,
) {

    suspend fun execute() {
        if (repository.getDownloadWorkerStarted().not()) {
            val startRateWork = OneTimeWorkRequestBuilder<DownloadHotPotsWorker>()
                .addTag(TAG)
                .build()
            workManager.enqueue(startRateWork)
            repository.setDownloadWorkerStarted(true)
        }
    }
}