package hu.bme.aut.ramapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hu.bme.aut.ramapp.network.CharacterService
import hu.bme.aut.ramapp.persistence.dao.RatingDao
import hu.bme.aut.ramapp.ui.main.MainRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        characterService: CharacterService,
        ratingDao: RatingDao
    ) : MainRepository {
        return MainRepository(characterService, ratingDao)
    }

}