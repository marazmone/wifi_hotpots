package com.marazmone.mapsclustertest.domain.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType
import kotlin.random.Random

data class AppMarker(
    val order: String,
    val id: String,
    val latLng: LatLng,
    val type: AppMarkerType,
) : ClusterItem {

    override fun getPosition(): LatLng = latLng

    override fun getTitle(): String {
        return "${type.name} #${Random.nextInt(100_000)}"
    }

    override fun getSnippet(): String {
        return "Password: ${randomPassword()}"
    }

    private fun randomPassword(): String {
        return (1..STRING_LENGTH)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    companion object {
        private const val STRING_LENGTH = 10;
        private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    }
}
