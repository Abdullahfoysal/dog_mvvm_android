package com.example.dogmvvm.data.api

import com.example.dogmvvm.data.models.DogResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface DogApi {
    @GET("breed/hound/images")
   suspend fun  getByBreed():Response<DogResponseModel>
}