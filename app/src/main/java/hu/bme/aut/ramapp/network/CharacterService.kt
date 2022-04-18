package hu.bme.aut.ramapp.network

import retrofit2.Response
import hu.bme.aut.ramapp.model.Character
import hu.bme.aut.ramapp.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>

}