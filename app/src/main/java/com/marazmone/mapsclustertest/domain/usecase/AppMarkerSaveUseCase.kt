package com.marazmone.mapsclustertest.domain.usecase

import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity
import com.marazmone.mapsclustertest.domain.repository.AppMarkerRepository

class AppMarkerSaveUseCase(
    private val repository: AppMarkerRepository,
) {

    suspend fun execute(marker: AppMarkerEntity) {
        repository.save(marker)
    }
}