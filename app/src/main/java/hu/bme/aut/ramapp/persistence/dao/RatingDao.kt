package hu.bme.aut.ramapp.persistence.dao

import androidx.room.*
import hu.bme.aut.ramapp.model.Rating

@Dao
interface RatingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRating(rating: Rating)

    @Query("SELECT * FROM Rating WHERE characterId = :cId")
    suspend fun getRatingForCharacter(cId: Int): Rating?

    @Delete
    suspend fun delRating(rating: Rating)

}