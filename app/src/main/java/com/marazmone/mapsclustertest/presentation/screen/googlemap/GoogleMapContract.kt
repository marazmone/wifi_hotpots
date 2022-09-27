package com.marazmone.mapsclustertest.presentation.screen.googlemap

import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType
import com.marazmone.mapsclustertest.domain.model.AppMarker
import com.marazmone.mapsclustertest.presentation.base.BaseViewAction
import com.marazmone.mapsclustertest.presentation.base.BaseViewEffect
import com.marazmone.mapsclustertest.presentation.base.BaseViewState

class GoogleMapContract {

    data class State(
        val items: List<AppMarker> = emptyList(),
    ) : BaseViewState

    sealed interface Action : BaseViewAction {

        object Remove : Action

        data class Result(val items: List<AppMarker>, val switches: List<AppMarkerType>) : Action
    }

    sealed interface Effect : BaseViewEffect
}