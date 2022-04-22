package hu.bme.aut.ramapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rating(
    @PrimaryKey
    val _id: Int,
    val characterId: Int,
    val review: String
)
