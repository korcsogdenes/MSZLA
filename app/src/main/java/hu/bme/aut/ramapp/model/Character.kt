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
    var rating: Rating = Rating(-1, 0, "No Review"),
    var firstEp: Episode,
    @SerializedName("episode")
    val episodeList: ArrayList<String>,
    var epListToShow: List<Episode>,
    @SerializedName("image")
    val img_url: String
){
    constructor() :this(0, "", "", "", Location("", ""), Origin("", ""),Rating(-1, 0, "No Review"), Episode("", ""), arrayListOf<String>(), listOf(), "")
}

data class Episode(
    @SerializedName("name")
    val name: String,
    @SerializedName("episode")
    val episode: String
)

data class Location(
    val name: String,
    val url: String
)

data class Origin(
    val name: String,
    val url: String
)
