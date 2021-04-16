package ru.igorsharov.rickandmortyapplication.ui.entities

import ru.igorsharov.rickandmortyapplication.core.api.entities.CharacterResponse
import ru.igorsharov.rickandmortyapplication.core.extension.empty

data class CharacterView(
    val name: String = String.empty(),
    val species: String = String.empty(),
    val status: String = String.empty(),
    val gender: String = String.empty(),
    val image: String = String.empty(),
    val location: String = String.empty()
) {
    companion object {
        fun empty() = CharacterView()
    }
}

fun CharacterResponse.mapToView() =
    CharacterView(name, species, status, gender, image, location.entries.first().value)