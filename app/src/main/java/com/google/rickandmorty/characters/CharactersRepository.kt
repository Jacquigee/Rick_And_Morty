package com.google.rickandmorty.characters


import com.google.rickandmorty.network.NetworkLayer

class CharactersRepository {

    suspend fun getCharacterList(pageIndex: Int): GetCharactersPageResponse? {
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful) {
        return null
        }

        return request.body
    }
}