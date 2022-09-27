package com.marazmone.mapsclustertest.presentation.manager

import android.content.Context
import androidx.core.content.edit
import com.marazmone.mapsclustertest.BuildConfig
import com.marazmone.mapsclustertest.domain.manager.SharedPreferenceManager

private const val SHARED_PREFS_NAME = BuildConfig.APPLICATION_ID.plus("_prefs")

private const val DOWNLOAD_WORKER_STARTED = "download_worker_started"

class SharedPreferenceManagerImpl(
    private val appContext: Context,
) : SharedPreferenceManager {

    private val sharedPrefs by lazy {
        appContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    override var downloadWorkerStarted: Boolean
        get() = sharedPrefs.getBoolean(DOWNLOAD_WORKER_STARTED, false)
        set(value) = sharedPrefs.edit { putBoolean(DOWNLOAD_WORKER_STARTED, value) }
}