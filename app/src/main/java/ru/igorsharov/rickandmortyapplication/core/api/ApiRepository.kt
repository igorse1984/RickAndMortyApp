package ru.igorsharov.rickandmortyapplication.core.api

import io.reactivex.Single
import ru.igorsharov.rickandmortyapplication.core.api.entities.*
import javax.inject.Inject

interface ApiRepository {

    fun getEndpoints(): Single<EndpointsResponse>
    fun getCharacters(page: Int): Single<CharactersResponse>
    fun getCharacter(characterId: Int): Single<CharacterResponse>
    fun getLocations(page: Int): Single<LocationsResponse>
    fun getLocations(url: String): Single<LocationsResponse>
    fun getLocation(locationId: Int): Single<LocationResponse>
    fun getEpisodes(page: Int): Single<EpisodesResponse>
    fun getEpisode(episodeId: Int): Single<EpisodeResponse>

    class ApiRepositoryImpl @Inject constructor(
        private val apiService: ApiService
    ) : ApiRepository {

        override fun getEndpoints() = apiService.getEndpoints()
        override fun getCharacters(page: Int) = apiService.getCharacters(page)
        override fun getCharacter(characterId: Int) = apiService.getCharacter(characterId)
        override fun getLocations(page: Int) = apiService.getLocations(page)
        override fun getLocations(url: String) = apiService.getLocations(url)
        override fun getLocation(locationId: Int) = apiService.getLocation(locationId)
        override fun getEpisodes(page: Int) = apiService.getEpisodes(page)
        override fun getEpisode(episodeId: Int) = apiService.getEpisode(episodeId)
    }
}