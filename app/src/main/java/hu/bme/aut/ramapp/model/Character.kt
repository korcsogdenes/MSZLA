package hu.bme.aut.ramapp.model

data class Character(
    val _id: Int,
    val name: String,
    val status: String,
    val species: String,
    val location: String,
    val origin: String,
    val firstEp: String,
    val episodeList: ArrayList<String>
)
