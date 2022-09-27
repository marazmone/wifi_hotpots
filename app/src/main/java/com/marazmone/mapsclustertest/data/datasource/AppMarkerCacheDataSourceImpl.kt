package com.marazmone.mapsclustertest.data.datasource

import com.marazmone.mapsclustertest.data.db.dao.AppMarkerDao
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity
import com.marazmone.mapsclustertest.domain.manager.SharedPreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppMarkerCacheDataSourceImpl(
    private val dao: AppMarkerDao,
    private val sharedPreferenceManager: SharedPreferenceManager,
) : AppMarkerCacheDataSource {

    override suspend fun save(entity: AppMarkerEntity) = dao.save(entity)

    override suspend fun save(entities: List<AppMarkerEntity>) = dao.save(entities)

    override suspend fun getByFilter(
        southwestLat: Double,
        southwestLng: Double,
        northeastLat: Double,
        northeastLng: Double,
    ): List<AppMarkerEntity> = dao.getByFilter(
        southwestLat,
        southwestLng,
        northeastLat,
        northeastLng
    )

    override suspend fun setDownloadWorkerStarted(value: Boolean) = withContext(Dispatchers.IO) {
        sharedPreferenceManager.downloadWorkerStarted = value
    }

    override suspend fun getDownloadWorkerStarted(): Boolean = withContext(Dispatchers.IO) {
        sharedPreferenceManager.downloadWorkerStarted
    }
}