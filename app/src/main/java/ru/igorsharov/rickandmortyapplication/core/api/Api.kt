package ru.igorsharov.rickandmortyapplication.core.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import ru.igorsharov.rickandmortyapplication.core.api.entities.*

interface Api {
    @GET("api/")
    fun getEndpoints(): Single<EndpointsResponse>

    @GET("character/")
    fun getCharacters(@Query("page") page: Int): Single<CharactersResponse>

    @GET("character/{id}")
    fun getCharacter(@Path("id") characterId: Int): Single<CharacterResponse>

    @GET("location/")
    fun getLocations(@Query("page") page: Int): Single<LocationsResponse>

    @GET
    fun getLocations(@Url url: String): Single<LocationsResponse>

    @GET("location/{id}")
    fun getLocation(@Path("id") locationId: Int): Single<LocationResponse>

    @GET("episode/")
    fun getEpisodes(@Query("page") page: Int): Single<EpisodesResponse>

    @GET("episode/{id}")
    fun getEpisode(@Path("id") episodeId: Int): Single<EpisodeResponse>
}