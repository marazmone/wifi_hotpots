package com.marazmone.mapsclustertest.di

import com.marazmone.mapsclustertest.presentation.manager.worker.DownloadHotPotsWorker
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val workManagerModule = module {
    worker {
        DownloadHotPotsWorker(get(), get(), get())
    }
}