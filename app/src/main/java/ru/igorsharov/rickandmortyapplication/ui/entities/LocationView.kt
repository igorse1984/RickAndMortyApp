package ru.igorsharov.rickandmortyapplication.ui.entities

import ru.igorsharov.rickandmortyapplication.core.api.entities.LocationResponse
import ru.igorsharov.rickandmortyapplication.core.extension.empty

data class LocationView(
    val name: String = String.empty(),
    val type: String = String.empty(),
    val dimension: String = String.empty()
) {
    companion object {
        fun empty() = LocationView()
    }
}

fun LocationResponse.mapToView() = LocationView(name, type, dimension)