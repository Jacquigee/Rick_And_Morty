package com.google.rickandmorty.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val rickMortyApiService:RickMortyApiService by lazy {
        retrofit.create(RickMortyApiService::class.java)
    }
    val apiClient = ApiClient(rickMortyApiService)
}
