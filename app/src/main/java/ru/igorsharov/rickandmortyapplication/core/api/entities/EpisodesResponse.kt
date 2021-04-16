package ru.igorsharov.rickandmortyapplication.core.api.entities

data class EpisodesResponse(
    val info: InfoResponse,
    val results: List<EpisodeResponse>
)