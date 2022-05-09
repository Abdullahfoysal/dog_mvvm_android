package com.example.dogmvvm.ui.dogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dogmvvm.data.DogRepository

class DogViewModelFactory(private val repository: DogRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DogViewModel(repository) as T
    }
}