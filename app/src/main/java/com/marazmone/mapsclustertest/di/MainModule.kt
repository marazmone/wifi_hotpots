package com.marazmone.mapsclustertest.di

import androidx.room.Room
import androidx.work.WorkManager
import com.marazmone.mapsclustertest.data.db.AppDB
import com.marazmone.mapsclustertest.domain.manager.SharedPreferenceManager
import com.marazmone.mapsclustertest.presentation.manager.SharedPreferenceManagerImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
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
    single {
        WorkManager.getInstance(get())
    }
    singleOf(::SharedPreferenceManagerImpl) { bind<SharedPreferenceManager>() }
}