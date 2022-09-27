package com.marazmone.mapsclustertest.di

import com.marazmone.mapsclustertest.data.repository.AppMarkerRepositoryImpl
import com.marazmone.mapsclustertest.di.MapperNamed.APP_MARKER_ENTITY_TO_MODEL
import com.marazmone.mapsclustertest.domain.repository.AppMarkerRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<AppMarkerRepository> {
        AppMarkerRepositoryImpl(
            cache = get(),
            mapper = get(qualifier = named(APP_MARKER_ENTITY_TO_MODEL))
        )
    }
}