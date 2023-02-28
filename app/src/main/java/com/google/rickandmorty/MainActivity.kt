package com.google.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.google.rickandmorty.network.NetworkLayer
import com.google.rickandmorty.network.RickMortyApiService
import com.google.rickandmorty.viewModel.SharedViewModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characterText = findViewById<TextView>(R.id.characterName)
        val headerImage = findViewById<AppCompatImageView>(R.id.characterImage)
        val statusText = findViewById<TextView>(R.id.status)
        val originText = findViewById<TextView>(R.id.originSubText)
        val speciesText = findViewById<TextView>(R.id.speciesSubText)
        val genderImage = findViewById<ImageView>(R.id.genderIcon)

        viewModel.refreshCharacter(23)
        viewModel.characterByIdLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccessful network call",
                    Toast.LENGTH_SHORT)
                    .show()
                return@observe
            }

            characterText.text = response.name
            statusText.text = response.status
            originText.text = response.origin.name
            speciesText.text = response.species

            if (response.gender.equals("male", true)){
                genderImage.setImageResource(R.drawable.ic_male_24)
            } else {
                genderImage.setImageResource(R.drawable.ic_female_24)
            }

            Picasso.get().load(response.image).into(headerImage)
        }

    }
}