package com.marazmone.mapsclustertest.di

import com.marazmone.mapsclustertest.presentation.screen.googlemap.GoogleMapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GoogleMapViewModel(get(), get()) }
}