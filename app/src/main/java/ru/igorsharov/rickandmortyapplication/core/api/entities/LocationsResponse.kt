package ru.igorsharov.rickandmortyapplication.core.api.entities

data class LocationsResponse(
    val info: InfoResponse,
    val results: List<LocationResponse>
)