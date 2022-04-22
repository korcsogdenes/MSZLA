package hu.bme.aut.ramapp.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.ramapp.model.Rating
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun testApiAll(){
        viewModelScope.launch {
            val res = mainRepository.getCharacters(1)
            if(res != null)
                for (c in res){
                    println(c.name)
                }
        }
    }

    fun testApiOne(){
        viewModelScope.launch {
            val res = mainRepository.getCharacter(1)
            if(res != null)
                println(res.name)
        }
    }

    fun testDbInsert(id: Int, cId: Int, rev: String){
        viewModelScope.launch {
            val r = Rating(id, cId, rev)
            mainRepository.saveRating(r)
        }
    }

    fun testDbGet(cId: Int){
        viewModelScope.launch {
            val res = mainRepository.getRating(cId)
            if(res != null)
                Log.e("INFO", res.review)
        }
    }

}