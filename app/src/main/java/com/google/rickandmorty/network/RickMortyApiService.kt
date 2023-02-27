package com.google.rickandmorty.network

import retrofit2.Call
import retrofit2.http.GET
interface RickMortyApiService{
    @GET("character/2")
    fun getCharacterById(): Call<Any>
}

