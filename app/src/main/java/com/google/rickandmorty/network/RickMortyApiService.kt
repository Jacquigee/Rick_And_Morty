package com.google.rickandmorty.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://rickandmortyapi.com/api"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RickMortyApiService{
    @GET("character")
    suspend fun getCharacter(): String
}

object RickMortyApi {
    val retrofitService : RickMortyApiService by lazy{
        retrofit.create(RickMortyApiService::class.java)
    }
}