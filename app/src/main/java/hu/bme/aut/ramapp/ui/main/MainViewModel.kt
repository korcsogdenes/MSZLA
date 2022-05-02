package hu.bme.aut.ramapp.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.ramapp.model.Character
import hu.bme.aut.ramapp.model.Rating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    var currentPage = 1
    private val characterList = mutableListOf<Character>()
    val characterListLD = MutableLiveData<List<Character>>()

    fun testApiAll(){
        viewModelScope.launch {
            val res = mainRepository.getCharacters(1)
            if(res != null){
                characterList.addAll(res)
                characterListLD.postValue(characterList)
            }
        }
    }

    fun loadFirstPage(){
        if(characterList.size == 0){
            loadNextCharacters()
        }
    }

    fun loadNextCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            val res = mainRepository.getCharacters(currentPage)
            if(res != null){
                characterList.addAll(res)
                characterListLD.postValue(listOf(characterList).flatten())
                currentPage++
            }
        }
    }

    /*fun testApiOne(){
        viewModelScope.launch {
            val res = mainRepository.getCharacter(1)
            println(res?.name)
        }
    }*/

}