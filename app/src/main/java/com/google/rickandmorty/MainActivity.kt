package com.google.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.rickandmorty.network.RickMortyApiService
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val rickMortyApiService:RickMortyApiService = retrofit.create(RickMortyApiService::class.java)

        rickMortyApiService.getCharacterById().enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.i("MainActivity", response.toString())
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.i("MainActivity", t.message ?: "Null message")
            }
        } )

    }
}