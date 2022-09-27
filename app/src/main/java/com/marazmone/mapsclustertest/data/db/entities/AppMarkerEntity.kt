package com.marazmone.mapsclustertest.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "table_app_marker",
    primaryKeys = ["lat", "lng"],
)
data class AppMarkerEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "order")
    val order: String,
    @ColumnInfo(name = "lat")
    val lat: Double,
    @ColumnInfo(name = "lng")
    val lng: Double,
    @ColumnInfo(name = "type")
    val type: AppMarkerType
)

enum class AppMarkerType {
    HOME, OFFICE, COFFEE, GYM, MALL;
}