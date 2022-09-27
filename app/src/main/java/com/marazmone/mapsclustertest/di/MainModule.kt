package com.marazmone.mapsclustertest.di

import androidx.room.Room
import com.marazmone.mapsclustertest.data.db.AppDB
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDB::class.java, AppDB.NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        get<AppDB>().appMarkerDao()
    }
}