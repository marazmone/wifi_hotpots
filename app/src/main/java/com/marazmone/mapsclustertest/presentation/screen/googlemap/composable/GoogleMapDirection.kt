package com.marazmone.mapsclustertest.presentation.screen.googlemap.composable

import androidx.compose.runtime.Composable
import com.marazmone.mapsclustertest.presentation.screen.googlemap.GoogleMapViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun GoogleMapDirection(
    viewModel: GoogleMapViewModel = getViewModel()
) {
    GoogleMapScreen(
        state = viewModel.state.value,
        onRemoteItems = viewModel::removeMarkers,
        onUpdateMarkers = viewModel::updateMarkers,
    )
}