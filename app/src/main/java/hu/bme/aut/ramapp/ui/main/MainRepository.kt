package hu.bme.aut.ramapp.ui.main

import android.util.Log
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
        val res = characterService.getCharacters(page)
        Log.e("ERROR", "IDEE")
        return if(res.isSuccessful){
            res.body()?.results
        } else{
            Log.e("ERROR", "Retrieving character list failed.")
            null
        }
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

    suspend fun saveCharacter(character: Character){
        characterService.postCharacter(character)
    }

    suspend fun updateCharacter(character: Character){
        characterService.putCharacter(character)
    }

    suspend fun deleteCharacter(id: Int){
        characterService.delCharacter(id)
    }

}