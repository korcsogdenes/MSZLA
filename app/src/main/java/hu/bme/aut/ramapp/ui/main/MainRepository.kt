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
        return if(res.isSuccessful){
            res.body()?.results
        } else{
            Log.e("ERROR", "Retrieving character list failed.")
            listOf()
        }
    }

    suspend fun getCharacter(id: Int): Character? {
        val res = characterService.getCharacter(id)
        if(res.isSuccessful){
            val r = characterService.getCharacter(id).body()
            val rat = ratingDao.getRatingForCharacter(r!!._id)
            r.rating = rat
            if(r.episodeList.size >= 0){
                r.firstEp = r.episodeList[0]
            }
            else{
                r.firstEp = "Unknown"
            }

            return r
        }
        else{
            Log.e("ERROR", "Retrieving character failed.")
            return null
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