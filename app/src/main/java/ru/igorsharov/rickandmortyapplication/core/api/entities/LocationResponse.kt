package ru.igorsharov.rickandmortyapplication.core.api.entities

class LocationResponse(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)