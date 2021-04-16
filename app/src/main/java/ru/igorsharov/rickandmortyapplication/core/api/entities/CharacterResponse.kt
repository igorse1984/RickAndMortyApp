package ru.igorsharov.rickandmortyapplication.core.api.entities

data class CharacterResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Map<String, String>,
    val location: Map<String, String>,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)