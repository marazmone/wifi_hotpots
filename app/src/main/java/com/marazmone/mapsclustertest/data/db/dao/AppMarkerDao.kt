package com.marazmone.mapsclustertest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity

private const val QUERY_GET_BY_FILTER = """
    SELECT * FROM table_app_marker 
    WHERE lat BETWEEN :southwestLat AND :northeastLat 
    AND lng BETWEEN :southwestLng AND :northeastLng
"""

@Dao
interface AppMarkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: AppMarkerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entities: List<AppMarkerEntity>)

    @Query(QUERY_GET_BY_FILTER)
    suspend fun getByFilter(
        southwestLat: Double,
        southwestLng: Double,
        northeastLat: Double,
        northeastLng: Double,
    ): List<AppMarkerEntity>
}