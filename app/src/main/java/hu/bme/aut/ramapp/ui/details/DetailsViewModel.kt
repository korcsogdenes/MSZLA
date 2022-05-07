package hu.bme.aut.ramapp.ui.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.ramapp.model.Character
import hu.bme.aut.ramapp.model.Rating
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    val character = MutableLiveData<Character>()
    var revText: String by mutableStateOf(value = " ")

    fun getCharcater(cId: Int){
        viewModelScope.launch {
            val res = detailsRepository.getCharacter(cId)
            if(res != null){
                character.value = res
                revText = res.rating.review
            }
        }
    }

    fun saveRating(cId: Int, p: Int){
        viewModelScope.launch {
            val r = Rating(cId, p, revText)
            detailsRepository.saveRating(r)
            val tmpChar = character.value?.copy()
            tmpChar?.rating = r
            character.value = tmpChar
        }
    }

    fun clearRating(){
        val tmpChar = character.value?.copy()
        tmpChar?.rating = Rating(-1, 0, "No Review")
        revText = "No Review"
        character.value = tmpChar
    }

    fun delRating(cId: Int){
        viewModelScope.launch {
            val rToDel = detailsRepository.getRating(cId)
            if(rToDel != null){
                detailsRepository.delRating(rToDel)
            }

        }
    }

    fun delWithClear(cId: Int){
        viewModelScope.launch {
            val rToDel = detailsRepository.getRating(cId)
            if(rToDel != null){
                detailsRepository.delRating(rToDel)
                clearRating()
            }

        }
    }

}