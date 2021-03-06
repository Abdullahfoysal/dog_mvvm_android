package com.example.dogmvvm.data

import com.example.dogmvvm.data.api.DogApi
import com.example.dogmvvm.data.models.DogResponseModel

class DogRepository private constructor( private  var dao: DogDao) {


    companion object{
        @Volatile private  var instance: DogRepository?=null
        fun getInstance(dao: DogDao) =
            instance ?: synchronized(this){
                instance ?: DogRepository(dao).also { instance =it }
            }
    }

    suspend fun  fetchAll()= dao.fetchAll()
    suspend fun  fetchByBreed(breedType:String)= dao.fetchByBreed(breedType)
    fun getDogs()= dao.getDogs()
}