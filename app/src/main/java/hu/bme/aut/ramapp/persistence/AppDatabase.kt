package hu.bme.aut.ramapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.ramapp.model.Rating
import hu.bme.aut.ramapp.persistence.dao.RatingDao

@Database(entities = [Rating::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ratingDao(): RatingDao

}