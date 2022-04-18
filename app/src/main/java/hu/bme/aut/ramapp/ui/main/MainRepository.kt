package hu.bme.aut.ramapp.ui.main

import hu.bme.aut.ramapp.model.Character
import hu.bme.aut.ramapp.network.CharacterService
import hu.bme.aut.ramapp.persistence.dao.RatingDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val characterService: CharacterService
) {

    suspend fun getCharacters(page: Int) : List<Character>? {
        return characterService.getCharacters(page).body()
    }

    suspend fun getCharacter(id: Int): Character? {
        return characterService.getCharacter(id).body()
    }

}