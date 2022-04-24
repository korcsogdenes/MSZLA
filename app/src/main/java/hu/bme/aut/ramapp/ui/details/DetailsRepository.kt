package hu.bme.aut.ramapp.ui.details

import hu.bme.aut.ramapp.model.Rating
import hu.bme.aut.ramapp.network.CharacterService
import hu.bme.aut.ramapp.persistence.AppDatabase
import hu.bme.aut.ramapp.persistence.dao.RatingDao
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val ratingDao: RatingDao
) {

    suspend fun saveRating(rating: Rating){
        ratingDao.insertRating(rating)
    }

    suspend fun delRating(rating: Rating){
        ratingDao.delRating(rating)
    }

}