package com.example.dogmvvm.utilities

import com.example.dogmvvm.data.DogDatabase
import com.example.dogmvvm.data.DogRepository
import com.example.dogmvvm.data.api.DogApi
import com.example.dogmvvm.data.api.RetrofitInstance
import com.example.dogmvvm.ui.dogs.DogViewModelFactory

object InjectorUtils {
    fun provideDogViewModelFactory(): DogViewModelFactory{
        val repository = DogRepository.getInstance(DogDatabase.getInstance(RetrofitInstance.api).dogDao)
        return  DogViewModelFactory(repository)
    }
}