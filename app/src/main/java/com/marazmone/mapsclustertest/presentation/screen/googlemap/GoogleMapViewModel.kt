package com.marazmone.mapsclustertest.presentation.screen.googlemap

import androidx.lifecycle.viewModelScope
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType
import com.marazmone.mapsclustertest.domain.usecase.AppMarkerGetAllUseCase
import com.marazmone.mapsclustertest.presentation.base.BaseViewModel
import com.marazmone.mapsclustertest.presentation.screen.googlemap.GoogleMapContract.Action
import com.marazmone.mapsclustertest.presentation.screen.googlemap.GoogleMapContract.Action.Remove
import com.marazmone.mapsclustertest.presentation.screen.googlemap.GoogleMapContract.Action.Result
import com.marazmone.mapsclustertest.presentation.screen.googlemap.GoogleMapContract.Effect
import com.marazmone.mapsclustertest.presentation.screen.googlemap.GoogleMapContract.State
import com.marazmone.mapsclustertest.presentation.usecase.StartDownloadHotpotsUseCase
import kotlinx.coroutines.launch

class GoogleMapViewModel(
    private val getAllUseCase: AppMarkerGetAllUseCase,
    private val startDownloadHotpotsUseCase: StartDownloadHotpotsUseCase,
) : BaseViewModel<State, Action, Effect>() {

    init {
        viewModelScope.launch {
            startDownloadHotpotsUseCase.execute()
        }
    }

    override fun setInitialState(): State = State()

    override fun onReduceState(action: Action): State = when (action) {
        is Result -> {
            currentState.copy(
                items = action.items.filter { appMarker ->
                    action.switches.contains(appMarker.type)
                }
            )
        }

        is Remove -> {
            currentState.copy(
                items = emptyList()
            )
        }
    }

    fun updateMarkers(
        switches: List<AppMarkerType>,
        southwestLat: Double,
        southwestLng: Double,
        northeastLat: Double,
        northeastLng: Double,
    ) {
        viewModelScope.launch {
            val markers = getAllUseCase.execute(
                southwestLat,
                southwestLng,
                northeastLat,
                northeastLng
            )
            sendAction { Result(markers, switches) }
        }
    }

    fun removeMarkers() {
        sendAction { Remove }
    }
}