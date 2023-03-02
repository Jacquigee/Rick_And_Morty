package com.google.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.rickandmorty.epoxy.CharacterDetailsEpoxyController
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

    private val epoxyController = CharacterDetailsEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.characterByIdLiveData.observe(this) { response ->

            epoxyController.characterResponse = response
            if (response == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccessful network call",
                    Toast.LENGTH_SHORT)
                    .show()
                return@observe
            }

        }

        viewModel.refreshCharacter(23)

        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecycerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }
}