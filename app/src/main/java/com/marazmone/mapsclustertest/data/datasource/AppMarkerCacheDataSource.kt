package com.marazmone.mapsclustertest.data.datasource

import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity

interface AppMarkerCacheDataSource {

    suspend fun save(entity: AppMarkerEntity)

    suspend fun save(entities: List<AppMarkerEntity>)

    suspend fun getAll(
        southwestLat: Double,
        southwestLng: Double,
        northeastLat: Double,
        northeastLng: Double,
    ): List<AppMarkerEntity>
}