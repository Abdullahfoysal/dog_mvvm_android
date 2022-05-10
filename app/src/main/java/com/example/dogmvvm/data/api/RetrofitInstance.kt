package com.example.dogmvvm.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL ="https://dog.ceo/api/"
const val AUTH_HEADER ="Authorization"
const val API_KEY ="KEY"

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor { chain ->
                    chain.proceed(chain
                        .request()
                        .newBuilder()
                        .addHeader(AUTH_HEADER,"BEARER $API_KEY")
                        .build()
                    )
                }
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: DogApi by lazy {
        retrofit
            .create(DogApi::class.java)
    }


}