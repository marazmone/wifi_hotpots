package com.marazmone.mapsclustertest.data.datasource

import com.marazmone.mapsclustertest.data.db.dao.AppMarkerDao
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity

class AppMarkerCacheDataSourceImpl(
    private val dao: AppMarkerDao,
) : AppMarkerCacheDataSource {

    override suspend fun save(entity: AppMarkerEntity) = dao.save(entity)

    override suspend fun save(entities: List<AppMarkerEntity>) = dao.save(entities)

    override suspend fun getAll(
        southwestLat: Double,
        southwestLng: Double,
        northeastLat: Double,
        northeastLng: Double,
    ): List<AppMarkerEntity> = dao.getAll(
        southwestLat,
        southwestLng,
        northeastLat,
        northeastLng
    )
}