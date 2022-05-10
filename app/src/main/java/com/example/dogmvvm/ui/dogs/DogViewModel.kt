package com.example.dogmvvm.ui.dogs

import androidx.lifecycle.*
import com.example.dogmvvm.data.DogRepository
import kotlinx.coroutines.launch

class DogViewModel (private val repository: DogRepository):ViewModel() {

     fun getDogs() =repository.getDogs()
     //suspend fun fetchDogs()=repository.fetchDog()
      fun fetchDogs() = viewModelScope.launch {
        repository.fetchAll()

      }
      fun fetchByBreed(breedType:String) = viewModelScope.launch {
        repository.fetchByBreed(breedType)

      }


}