package com.marazmone.mapsclustertest.presentation.screen.googlemap.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.marazmone.mapsclustertest.R.drawable
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.HOME
import com.marazmone.mapsclustertest.domain.model.AppMarkerSwitch
import com.marazmone.mapsclustertest.presentation.screen.googlemap.GoogleMapContract
import com.marazmone.mapsclustertest.presentation.screen.googlemap.GoogleMapContract.State
import com.marazmone.mapsclustertest.presentation.ui.theme.MapsClusterTestTheme

@Preview
@Composable
fun GoogleMapScreen_Preview() {
    MapsClusterTestTheme {
        GoogleMapScreen(
            state = State(),
            onRemoteItems = { },
            onUpdateMarkers = { _, _, _, _, _ -> }
        )
    }
}

@Preview
@Composable
fun SwitchItem_Enabled_Preview() {
    MapsClusterTestTheme {
        AppMarkerSwitchItem(
            item = AppMarkerSwitch(drawable.ic_home, HOME),
            onClick = {}
        )
    }
}

@Preview
@Composable
fun SwitchItem_Disabled_Preview() {
    MapsClusterTestTheme {
        AppMarkerSwitchItem(
            item = AppMarkerSwitch(drawable.ic_home, HOME).apply {
                enabled = false
            },
            onClick = {}
        )
    }
}