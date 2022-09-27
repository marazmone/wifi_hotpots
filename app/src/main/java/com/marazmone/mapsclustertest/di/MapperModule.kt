package com.marazmone.mapsclustertest.di

import com.marazmone.mapsclustertest.data.db.entities.AppMarkerEntity
import com.marazmone.mapsclustertest.data.mapper.AppMarkerEntityToModel
import com.marazmone.mapsclustertest.di.MapperNamed.APP_MARKER_ENTITY_TO_MODEL
import com.marazmone.mapsclustertest.domain.mapper.Mapper
import com.marazmone.mapsclustertest.domain.model.AppMarker
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.named
import org.koin.dsl.module

val mapperModule = module {
    factoryOf(::AppMarkerEntityToModel) {
        named(APP_MARKER_ENTITY_TO_MODEL)
        bind<Mapper<AppMarkerEntity, AppMarker>>()
    }
}

object MapperNamed {
    // Its bad code but I did not find better result for koin
    const val APP_MARKER_ENTITY_TO_MODEL = "app_marker_entity_to_model"
}