package com.marazmone.mapsclustertest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marazmone.mapsclustertest.data.db.dao.AppMarkerDao
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity

@Database(
    entities = [
        AppMarkerEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDB : RoomDatabase() {

    abstract fun appMarkerDao(): AppMarkerDao

    companion object {
        const val NAME = "app_database"
    }
}