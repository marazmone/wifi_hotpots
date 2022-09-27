package com.marazmone.mapsclustertest.presentation.ui

import android.app.PendingIntent
import android.os.Build

val Double?.orZero: Double get() = this ?: 0.0

val Int?.orZero: Int get() = this ?: 0

val pendingIntentFlag: Int = if (isAtLeastSdkVersion(Build.VERSION_CODES.S)) {
    PendingIntent.FLAG_MUTABLE
} else {
    0
}

private fun isAtLeastSdkVersion(versionCode: Int): Boolean {
    return Build.VERSION.SDK_INT >= versionCode
}