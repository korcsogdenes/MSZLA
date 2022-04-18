package hu.bme.aut.ramapp.model

import com.google.gson.annotations.SerializedName

class CharacterResponse {

    @SerializedName("results")
    var results: List<Character>? = null

}