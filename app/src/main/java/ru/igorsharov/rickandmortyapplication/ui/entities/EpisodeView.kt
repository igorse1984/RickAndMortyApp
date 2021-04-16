package ru.igorsharov.rickandmortyapplication.ui.entities

import ru.igorsharov.rickandmortyapplication.core.api.entities.EpisodeResponse
import ru.igorsharov.rickandmortyapplication.core.extension.empty

data class EpisodeView(
    val name: String = String.empty(),
    val airDate: String = String.empty(),
    val episode: String = String.empty()
) {
    companion object {
        fun empty() = EpisodeView()
    }
}

fun EpisodeResponse.mapToView() = EpisodeView(name, airDate, episode)