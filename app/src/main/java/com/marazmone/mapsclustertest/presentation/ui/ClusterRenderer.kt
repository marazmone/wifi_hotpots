package com.marazmone.mapsclustertest.presentation.ui

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.marazmone.mapsclustertest.R
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.COFFEE
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.GYM
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.HOME
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.MALL
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType.OFFICE
import com.marazmone.mapsclustertest.domain.model.AppMarker

class ClusterRenderer(
    context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<AppMarker>,
) : DefaultClusterRenderer<AppMarker>(context, map, clusterManager) {

    init {
        clusterManager.renderer = this
    }

    override fun onBeforeClusterItemRendered(item: AppMarker, markerOptions: MarkerOptions) {
        when (item.type) {
            HOME -> markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_home))
            OFFICE -> markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_office))
            COFFEE -> markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_coffee))
            GYM -> markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_gym))
            MALL -> markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mall))
        }
    }
}