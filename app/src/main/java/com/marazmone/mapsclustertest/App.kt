package com.marazmone.mapsclustertest

import android.app.Application
import com.marazmone.mapsclustertest.di.dataSourceModule
import com.marazmone.mapsclustertest.di.databaseModule
import com.marazmone.mapsclustertest.di.mapperModule
import com.marazmone.mapsclustertest.di.repositoryModule
import com.marazmone.mapsclustertest.di.useCaseModule
import com.marazmone.mapsclustertest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                databaseModule,
                dataSourceModule,
                mapperModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
            )
        }
    }
}