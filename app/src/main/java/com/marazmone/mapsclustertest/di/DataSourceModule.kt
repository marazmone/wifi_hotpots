package com.marazmone.mapsclustertest.di

import com.marazmone.mapsclustertest.data.datasource.AppMarkerCacheDataSource
import com.marazmone.mapsclustertest.data.datasource.AppMarkerCacheDataSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataSourceModule = module {
    singleOf(::AppMarkerCacheDataSourceImpl) {
        bind<AppMarkerCacheDataSource>()
    }
}