package hu.bme.aut.ramapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rating(
    val characterId: Int,
    val points: Int = 0,
    val review: String
) {
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0

    companion object {

        fun mock(): Rating{
            val r = Rating(
                characterId = 1,
                points = 4,
                review = "Wabalanadubub"
            )
            return r
        }

    }

}
