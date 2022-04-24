package hu.bme.aut.ramapp.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    val _id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("origin")
    val origin: Origin,
    var rating: Rating?,
    var firstEp: String,
    @SerializedName("episode")
    val episodeList: ArrayList<String>,
    @SerializedName("image")
    val img_url: String
)

data class Location(
    val name: String,
    val url: String
)

data class Origin(
    val name: String,
    val url: String
)
