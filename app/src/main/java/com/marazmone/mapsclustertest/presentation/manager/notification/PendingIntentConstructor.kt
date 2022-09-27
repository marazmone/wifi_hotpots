package com.marazmone.mapsclustertest.presentation.manager.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.marazmone.mapsclustertest.BuildConfig
import com.marazmone.mapsclustertest.presentation.MainActivity
import com.marazmone.mapsclustertest.presentation.ui.pendingIntentFlag

object PendingIntentConstructor {

    fun buildPendingIntent(context: Context): PendingIntent? {
        val newIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            action = BuildConfig.APPLICATION_ID.plus(Math.random())
        }
        return PendingIntent.getActivity(context, 0, newIntent, pendingIntentFlag)
    }
}