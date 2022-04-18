package hu.bme.aut.ramapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun testApiAll(){
        viewModelScope.launch {
            val res = mainRepository.getCharacters(2)
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

}