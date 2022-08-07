package com.google.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

const val BASE_URL = "https://rickandmortyapi.com/api"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}