package hu.bme.aut.ramapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rating(
    @PrimaryKey
    val _id: Int,
    val characterId: Int,
    val points: Int,
    val review: String
) {

    companion object {

        fun mock() = Rating(
            _id = 0,
            characterId = 1,
            points = 4,
            review = "Wabalanadubub"
        )

    }

}
