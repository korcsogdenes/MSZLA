package hu.bme.aut.ramapp.ui.details

import hu.bme.aut.ramapp.network.CharacterService
import hu.bme.aut.ramapp.persistence.AppDatabase
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    characterService: CharacterService,
    appDatabase: AppDatabase
) {
}