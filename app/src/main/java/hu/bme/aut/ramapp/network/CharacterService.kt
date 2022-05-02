package hu.bme.aut.ramapp.network

import retrofit2.Response
import hu.bme.aut.ramapp.model.Character
import hu.bme.aut.ramapp.model.CharacterResponse
import hu.bme.aut.ramapp.model.Episode
import retrofit2.http.*

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>

    @GET("episode/{ids}")
    suspend fun getEpisodes(@Path("ids") ids: String): Response<List<Episode>>

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: String): Response<Episode>

    @POST("character/new")
    suspend fun postCharacter(@Body character: Character)

    @PUT("character/update")
    suspend fun putCharacter(@Body character: Character)

    @DELETE("character/delete/{id}")
    suspend fun delCharacter(@Path("id") id: Int)
}