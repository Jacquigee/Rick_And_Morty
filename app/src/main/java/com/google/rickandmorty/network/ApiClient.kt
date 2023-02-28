package com.google.rickandmorty.network


import com.google.rickandmorty.GetCharacterByIdResponse
import retrofit2.Response

class ApiClient(
    private val rickMortyApiService: RickMortyApiService
) {
    suspend fun getCharacterById(characterId: Int): Response<GetCharacterByIdResponse> {
        return rickMortyApiService.getCharacterById(characterId)
    }
}