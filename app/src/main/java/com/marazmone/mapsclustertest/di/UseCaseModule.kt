package com.marazmone.mapsclustertest.di

import com.marazmone.mapsclustertest.domain.usecase.AppMarkerGetAllUseCase
import com.marazmone.mapsclustertest.domain.usecase.AppMarkerSaveAllUseCase
import com.marazmone.mapsclustertest.domain.usecase.AppMarkerSaveUseCase
import com.marazmone.mapsclustertest.presentation.usecase.LoadHotpotsFromAssetToDBUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::AppMarkerGetAllUseCase)
    factoryOf(::AppMarkerSaveAllUseCase)
    factoryOf(::AppMarkerSaveUseCase)
    factoryOf(::LoadHotpotsFromAssetToDBUseCase)
}