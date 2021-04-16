package ru.igorsharov.rickandmortyapplication.core.api.entities

data class CharactersResponse(
    val info: InfoResponse,
    val results: List<CharacterResponse>
)