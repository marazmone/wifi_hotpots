package com.marazmone.mapsclustertest.data.datasource

import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity

interface AppMarkerCacheDataSource {

    suspend fun save(entity: AppMarkerEntity)

    suspend fun save(entities: List<AppMarkerEntity>)

    suspend fun getByFilter(
        southwestLat: Double,
        southwestLng: Double,
        northeastLat: Double,
        northeastLng: Double,
    ): List<AppMarkerEntity>

    suspend fun setDownloadWorkerStarted(value: Boolean)

    suspend fun getDownloadWorkerStarted(): Boolean
}