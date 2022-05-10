package com.example.dogmvvm.data.api

import com.example.dogmvvm.data.models.DogResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("breed/hound/images")
   suspend fun  fetchAll():Response<DogResponseModel>

    @GET("breed/{breedType}/images/random/50")
    suspend fun getByBreed(@Path("breedType")breedType:String):Response<DogResponseModel>
}