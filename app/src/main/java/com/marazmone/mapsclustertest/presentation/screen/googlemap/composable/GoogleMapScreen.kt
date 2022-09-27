package com.marazmone.mapsclustertest.presentation.screen.googlemap.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.rememberCameraPositionState
import com.marazmone.mapsclustertest.R.drawable
import com.marazmone.mapsclustertest.R.string
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.COFFEE
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.GYM
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.HOME
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.MALL
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.OFFICE
import com.marazmone.mapsclustertest.domain.model.AppMarker
import com.marazmone.mapsclustertest.domain.model.AppMarkerSwitch
import com.marazmone.mapsclustertest.presentation.screen.googlemap.GoogleMapContract.State
import com.marazmone.mapsclustertest.presentation.ui.ClusterRenderer
import com.marazmone.mapsclustertest.presentation.ui.orZero
import kotlinx.coroutines.launch

private val CENTER = LatLng(0.0, 0.0)
private val MY_LOCATION = LatLng(38.919056, -8.867987)

private const val ZOOM_FOR_VISIBLE_MARKERS = 13f
private const val MOVE_CAMERA_ANIM_DURATION = 1000
private const val DISTANCE_CLUSTERING = 150

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun GoogleMapScreen(
    state: State,
    onRemoteItems: () -> Unit,
    onUpdateMarkers: (
        switches: List<AppMarkerType>,
        southwestLat: Double,
        southwestLng: Double,
        northeastLat: Double,
        northeastLng: Double,
    ) -> Unit,
) {
    val context = LocalContext.current
    var zoomLevelState by remember { mutableStateOf(0f) }
    var southwestLat by remember { mutableStateOf(0.0) }
    var southwestLng by remember { mutableStateOf(0.0) }
    var northeastLat by remember { mutableStateOf(0.0) }
    var northeastLng by remember { mutableStateOf(0.0) }
    var clusterManager by remember { mutableStateOf<ClusterManager<AppMarker>?>(null) }
    val switches by remember {
        mutableStateOf(
            listOf(
                AppMarkerSwitch(drawable.ic_home, HOME),
                AppMarkerSwitch(drawable.ic_office, OFFICE),
                AppMarkerSwitch(drawable.ic_gym, GYM),
                AppMarkerSwitch(drawable.ic_coffee, COFFEE),
                AppMarkerSwitch(drawable.ic_mall, MALL),
            )
        )
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(CENTER, 1f)
    }
    val scope = rememberCoroutineScope()
    Box {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            MapEffect(Unit) { map ->
                map.setPadding(0, 100, 0, 50)
            }
            MapEffect(cameraPositionState.isMoving) { map ->
                map.setOnCameraIdleListener {
                    zoomLevelState = map.cameraPosition.zoom
                    val latLngBounds = cameraPositionState.projection?.visibleRegion?.latLngBounds
                    southwestLat = latLngBounds?.southwest?.latitude.orZero
                    southwestLng = latLngBounds?.southwest?.longitude.orZero
                    northeastLat = latLngBounds?.northeast?.latitude.orZero
                    northeastLng = latLngBounds?.northeast?.longitude.orZero
                    if (zoomLevelState >= ZOOM_FOR_VISIBLE_MARKERS) {
                        onUpdateMarkers.invoke(
                            switches.filter { it.enabled }.map { it.type },
                            southwestLat,
                            southwestLng,
                            northeastLat,
                            northeastLng,
                        )
                    } else {
                        onRemoteItems.invoke()
                    }
                }
            }
            MapEffect(key1 = state.items) { map ->
                if (clusterManager == null) {
                    clusterManager = ClusterManager<AppMarker>(context, map).apply {
                        ClusterRenderer(context, map, this)
                        algorithm = NonHierarchicalDistanceBasedAlgorithm<AppMarker>().apply {
                            setMaxDistanceBetweenClusteredItems(DISTANCE_CLUSTERING)
                        }
                    }
                }
                clusterManager?.apply {
                    clearItems()
                    addItems(state.items)
                    cluster()
                    onCameraIdle()
                }
            }
        }
        var visibleFilter by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .navigationBarsPadding()
                .padding(bottom = 32.dp)
                .padding(start = 6.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = if (visibleFilter) drawable.ic_close else drawable.ic_filter),
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                visibleFilter = visibleFilter.not()
                            }
                    )
                    LazyColumn {
                        items(if (visibleFilter) switches else emptyList()) { appMarkerSwitch ->
                            AppMarkerSwitchItem(appMarkerSwitch) {
                                onUpdateMarkers.invoke(
                                    switches.filter { it.enabled }.map { it.type },
                                    southwestLat,
                                    southwestLng,
                                    northeastLat,
                                    northeastLng,
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(12.dp))
            Image(
                painter = painterResource(id = drawable.ic_location),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .clickable {
                        scope.launch {
                            cameraPositionState.animate(
                                update = CameraUpdateFactory.newLatLngZoom(
                                    MY_LOCATION,
                                    ZOOM_FOR_VISIBLE_MARKERS
                                ),
                                durationMs = MOVE_CAMERA_ANIM_DURATION
                            )
                        }
                    }
            )
            Spacer(modifier = Modifier.size(12.dp))
            Box(
                modifier = Modifier
                    .height(64.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                val text = if (zoomLevelState < ZOOM_FOR_VISIBLE_MARKERS) {
                    stringResource(string.zoom_in_for_result)
                } else {
                    val count = if (state.items.size > 100) {
                        stringResource(string.result_100_plus)
                    } else {
                        state.items.size.toString()
                    }
                    stringResource(string.hotpots_found, count)
                }
                Text(text = text)
            }
        }
    }
}