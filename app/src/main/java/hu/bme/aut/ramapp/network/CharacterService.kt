package hu.bme.aut.ramapp.network

import retrofit2.Response
import hu.bme.aut.ramapp.model.Character
import hu.bme.aut.ramapp.model.CharacterResponse
import retrofit2.http.*

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>

    @POST("character/new")
    suspend fun insertCharacter(@Body character: Character)

    @DELETE("character/delete/{id}")
    suspend fun delCharacter(@Path("id") id: Int)
}