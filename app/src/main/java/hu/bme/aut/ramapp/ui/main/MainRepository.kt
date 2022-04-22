package hu.bme.aut.ramapp.ui.main

import hu.bme.aut.ramapp.model.Character
import hu.bme.aut.ramapp.model.Rating
import hu.bme.aut.ramapp.network.CharacterService
import hu.bme.aut.ramapp.persistence.dao.RatingDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val characterService: CharacterService,
    private val ratingDao: RatingDao
) {

    suspend fun getCharacters(page: Int) : List<Character>? {
        return characterService.getCharacters(page).body()?.results
    }

    suspend fun getCharacter(id: Int): Character {
        val r = characterService.getCharacter(id).body()
        val rat = ratingDao.getRatingForCharacter(r!!._id)
        r.rating = rat

        return r
    }

    suspend fun saveRating(rating: Rating){
        ratingDao.insertRating(rating)
    }

    suspend fun getRating(cId: Int): Rating? {
        return ratingDao.getRatingForCharacter(cId)
    }

    suspend fun delRating(rating: Rating){
        ratingDao.delRating(rating)
    }

}