package ru.igorsharov.rickandmortyapplication.core.api.entities

import com.squareup.moshi.Json
import ru.igorsharov.rickandmortyapplication.core.extension.empty

class EpisodeResponse(
    val id: Int,
    val name: String,
    @Json(name = "air_date") private val _airDate: String?,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) {
    val airDate = _airDate ?: String.empty()
}