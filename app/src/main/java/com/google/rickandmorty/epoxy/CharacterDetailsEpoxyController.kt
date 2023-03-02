package com.google.rickandmorty.epoxy

import com.airbnb.epoxy.EpoxyController
import com.google.rickandmorty.GetCharacterByIdResponse
import com.google.rickandmorty.R
import com.google.rickandmorty.databinding.ModelCharacterDetailsDataPointBinding
import com.google.rickandmorty.databinding.ModelCharacterDetailsHeaderBinding
import com.google.rickandmorty.databinding.ModelCharacterDetailsImageBinding
import com.squareup.picasso.Picasso
import retrofit2.http.Url

class CharacterDetailsEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
    set(value) {
        field = value
        if (field) {
            requestModelBuild()
        }
    }

    var characterResponse: GetCharacterByIdResponse? = null
    set(value) {
        field = value
        if (field != null) {
            isLoading = false
            requestModelBuild()
        }
    }

    override fun buildModels() {

        if (isLoading) {
            //show loading state
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (characterResponse == null){
            return
        }
        //add header model
        HeaderEpoxyModel(
            name = characterResponse!!.name,
            gender = characterResponse!!.gender,
            status = characterResponse!!.status
        ).id("header").addTo(this)
        //add image model
        ImageEpoxyModel(
            imageUrl = characterResponse!!.image
        ).id("image").addTo(this)

        //add the date point model
        DataPointEpoxyModel(
            title = "Origin",
            description = characterResponse!!.origin.name
        ).id("data_point_1").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = characterResponse!!.species
        ).id("data_point_2").addTo(this)
    }

    data class HeaderEpoxyModel(
        val name: String,
        val gender: String,
        val status: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header) {
        override fun ModelCharacterDetailsHeaderBinding.bind() {
            characterName.text = name
            characterStatus.text = status

            if (gender.equals("male", true)){
                genderIcon.setImageResource(R.drawable.ic_male_24)
            } else {
                genderIcon.setImageResource(R.drawable.ic_female_24)
            }


        }
    }

    data class ImageEpoxyModel(
        val imageUrl: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsImageBinding>(R.layout.model_character_details_image){
        override fun ModelCharacterDetailsImageBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImage)
        }
    }

    data class DataPointEpoxyModel(
        val title: String,
        val description: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point){
        override fun ModelCharacterDetailsDataPointBinding.bind() {
            labelTextView.text = title
            descriptionTv.text = description
        }
    }
}