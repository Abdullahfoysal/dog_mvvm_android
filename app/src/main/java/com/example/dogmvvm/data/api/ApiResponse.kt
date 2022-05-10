package com.example.dogmvvm.data.api

import com.example.dogmvvm.data.models.DogResponseModel
import java.lang.Exception

sealed class ApiResponse<T>(
   data: T?=null,
   exception: Exception? =null
){
    data class Success<T>(val data:T): ApiResponse<T>(data,null)
    data class  Error<T>(val exception: Exception):ApiResponse<T>(null,exception)
}