package com.marazmone.mapsclustertest.domain.usecase

import com.marazmone.mapsclustertest.domain.model.AppMarker
import com.marazmone.mapsclustertest.domain.repository.AppMarkerRepository

class AppMarkerGetAllUseCase(
    private val repository: AppMarkerRepository,
) {

    suspend fun execute(
        southwestLat: Double,
        southwestLng: Double,
        northeastLat: Double,
        northeastLng: Double,
    ): List<AppMarker> = repository.getAll(
        southwestLat,
        southwestLng,
        northeastLat,
        northeastLng
    )
}