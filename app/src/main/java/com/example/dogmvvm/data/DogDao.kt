package com.example.dogmvvm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dogmvvm.data.api.ApiResponse
import com.example.dogmvvm.data.api.DogApi
import com.example.dogmvvm.data.models.DogResponseModel
import java.lang.Exception

class DogDao(private  val dogApi: DogApi){
   // private val dogList = mutableListOf<DogResponseModel>()
    private val dogs = MutableLiveData<ApiResponse<DogResponseModel?>>()
    init {

    }
    suspend fun fetchAll(){
        try {
            var response = dogApi.fetchAll()
            if(response.isSuccessful && response.body()!=null){
                dogs.value= ApiResponse.Success(response.body())
            }
        }catch (e: Exception){
            dogs.value= ApiResponse.Error(e)
        }



    }
    suspend fun fetchByBreed(breedType:String){
        try {
            var response = dogApi.getByBreed(breedType)
            if(response.isSuccessful && response.body()!=null){
                dogs.value= ApiResponse.Success(response.body())
            }
        }catch (e: Exception){
            dogs.value= ApiResponse.Error(e)
        }



    }
     fun getDogs()= dogs as LiveData<ApiResponse<DogResponseModel?>>

}