package com.example.dogmvvm.data

import com.example.dogmvvm.data.api.DogApi

class DogDatabase private  constructor(private val  dogApi: DogApi){

    var dogDao = DogDao(dogApi)
        private  set
    companion object{
        @Volatile private var instance: DogDatabase?=null
        fun getInstance(dogApi: DogApi) =
            instance ?: synchronized(this){
                instance ?: DogDatabase(dogApi).also { instance=it }
            }
    }


}