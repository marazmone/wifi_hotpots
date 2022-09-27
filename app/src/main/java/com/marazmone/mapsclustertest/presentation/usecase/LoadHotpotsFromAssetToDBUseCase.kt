package com.marazmone.mapsclustertest.presentation.usecase

import android.content.Context
import android.util.Log
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity
import com.marazmone.mapsclustertest.data.db.entities.AppMarkerType
import com.marazmone.mapsclustertest.domain.usecase.AppMarkerSaveUseCase
import com.marazmone.mapsclustertest.presentation.ui.orZero
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val FILE_NAME = "hotspots.csv"

class LoadHotpotsFromAssetToDBUseCase(
    private val context: Context,
    private val saveUseCase: AppMarkerSaveUseCase,
) {

    suspend fun execute() {
        withContext(Dispatchers.IO) {
            val isr = InputStreamReader(context.assets.open(FILE_NAME))
            val reader = BufferedReader(isr)
            reader.readLine()
            var line = ""
            var st: StringTokenizer?
            while (reader.readLine()?.also { line = it } != null) {
                try {
                    st = StringTokenizer(line, ",")
                    val order = st.nextToken()
                    val id = st.nextToken()
                    val lat = st.nextToken().toDoubleOrNull().orZero
                    val lng = st.nextToken().toDoubleOrNull().orZero
                    val appMarker = AppMarkerEntity(
                        order = order,
                        id = id,
                        lat = lat,
                        lng = lng,
                        type = randomTypeMarker(order)
                    )
                    saveUseCase.execute(appMarker)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun randomTypeMarker(order: String): AppMarkerType {
        val formattedOrder = order.toIntOrNull().orZero
        return when {
            formattedOrder % 5 == 0 -> AppMarkerType.MALL
            formattedOrder % 4 == 0 -> AppMarkerType.GYM
            formattedOrder % 3 == 0 -> AppMarkerType.HOME
            formattedOrder % 2 == 0 -> AppMarkerType.COFFEE
            else -> AppMarkerType.OFFICE
        }
    }
}