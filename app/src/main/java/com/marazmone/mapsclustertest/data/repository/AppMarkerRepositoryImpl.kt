package com.marazmone.mapsclustertest.data.repository

import com.marazmone.mapsclustertest.data.datasource.AppMarkerCacheDataSource
import com.marazmone.mapsclustertest.data.datasource.AppMarkerCacheDataSourceImpl
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity
import com.marazmone.mapsclustertest.domain.mapper.Mapper
import com.marazmone.mapsclustertest.domain.model.AppMarker
import com.marazmone.mapsclustertest.domain.repository.AppMarkerRepository

class AppMarkerRepositoryImpl(
    private val cache: AppMarkerCacheDataSource,
    private val mapper: Mapper<AppMarkerEntity, AppMarker>,
) : AppMarkerRepository {

    override suspend fun save(marker: AppMarkerEntity) = cache.save(marker)

    override suspend fun save(markers: List<AppMarkerEntity>) = cache.save(markers)

    override suspend fun getAll(
        southwestLat: Double,
        southwestLng: Double,
        northeastLat: Double,
        northeastLng: Double,
    ): List<AppMarker> = mapper.listAsync(
        cache.getAll(
            southwestLat,
            southwestLng,
            northeastLat,
            northeastLng
        )
    )

    override suspend fun setDownloadWorkerStarted(value: Boolean) {
        cache.setDownloadWorkerStarted(value)
    }

    override suspend fun getDownloadWorkerStarted(): Boolean = cache.getDownloadWorkerStarted()
}