package com.marazmone.mapsclustertest.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType

data class AppMarkerSwitch(
    @DrawableRes val drawableRes: Int,
    val type: AppMarkerType,
) {
    var enabled by mutableStateOf(true)
}