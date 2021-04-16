package ru.igorsharov.rickandmortyapplication.core.api.entities

data class EndpointsResponse(
    val characters: String,
    val locations: String,
    val episodes: String
)