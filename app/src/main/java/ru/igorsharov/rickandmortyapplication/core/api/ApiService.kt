package ru.igorsharov.rickandmortyapplication.core.api

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiService @Inject constructor(
    retrofit: Retrofit
) : Api {
    private val api by lazy { retrofit.create(Api::class.java) }

    override fun getEndpoints() = api.getEndpoints()
    override fun getCharacters(page: Int) = api.getCharacters(page)
    override fun getCharacter(characterId: Int) = api.getCharacter(characterId)
    override fun getLocations(page: Int) = api.getLocations(page)
    override fun getLocations(url: String) = api.getLocations(url)
    override fun getLocation(locationId: Int) = api.getLocation(locationId)
    override fun getEpisodes(page: Int) = api.getEpisodes(page)
    override fun getEpisode(episodeId: Int) = api.getEpisode(episodeId)
}