package ru.igorsharov.rickandmortyapplication.core.api.entities

data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)