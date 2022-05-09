package com.example.dogmvvm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dogmvvm.data.api.DogApi
import com.example.dogmvvm.data.models.DogResponseModel

class DogDao(private  val dogApi: DogApi){
   // private val dogList = mutableListOf<DogResponseModel>()
    private val dogs = MutableLiveData<DogResponseModel>()
    init {

    }
    suspend fun fetchDog(){
        var newDog = dogApi.getByBreed().body()!!
        dogs.value= newDog
    }
     fun getDogs()= dogs as LiveData<DogResponseModel>

}