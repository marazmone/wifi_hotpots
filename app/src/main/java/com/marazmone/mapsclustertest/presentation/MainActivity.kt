package com.marazmone.mapsclustertest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.rememberCameraPositionState
import com.marazmone.mapsclustertest.R
import com.marazmone.mapsclustertest.R.drawable
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.COFFEE
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.GYM
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.HOME
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.MALL
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.OFFICE
import com.marazmone.mapsclustertest.domain.model.AppMarker
import com.marazmone.mapsclustertest.domain.model.AppMarkerSwitch
import com.marazmone.mapsclustertest.presentation.screen.googlemap.GoogleMapContract.State
import com.marazmone.mapsclustertest.presentation.screen.googlemap.composable.GoogleMapDirection
import com.marazmone.mapsclustertest.presentation.ui.ClusterRenderer
import com.marazmone.mapsclustertest.presentation.ui.orZero
import com.marazmone.mapsclustertest.presentation.ui.theme.MapsClusterTestTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MapsClusterTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GoogleMapDirection()
                }
            }
        }
    }
}