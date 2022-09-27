package com.marazmone.mapsclustertest.data.mapper

import com.google.android.gms.maps.model.LatLng
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity
import com.marazmone.mapsclustertest.domain.mapper.Mapper
import com.marazmone.mapsclustertest.domain.model.AppMarker

class AppMarkerEntityToModel : Mapper<AppMarkerEntity, AppMarker> {

    override fun map(source: AppMarkerEntity): AppMarker =
        AppMarker(
            order = source.order,
            id = source.id,
            latLng = LatLng(source.lat, source.lng),
            type = source.type
        )
}