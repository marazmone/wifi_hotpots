package com.marazmone.mapsclustertest.domain.repository

import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity
import com.marazmone.mapsclustertest.domain.model.AppMarker

interface AppMarkerRepository {

    suspend fun save(marker: AppMarkerEntity)

    suspend fun save(markers: List<AppMarkerEntity>)

    suspend fun getAll(
        southwestLat: Double,
        southwestLng: Double,
        northeastLat: Double,
        northeastLng: Double,
    ): List<AppMarker>
}