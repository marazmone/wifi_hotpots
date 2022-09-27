package com.marazmone.mapsclustertest.domain.usecase

import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity
import com.marazmone.mapsclustertest.domain.repository.AppMarkerRepository

class AppMarkerSaveAllUseCase(
    private val repository: AppMarkerRepository,
) {

    suspend fun execute(markers: List<AppMarkerEntity>) {
        repository.save(markers)
    }
}