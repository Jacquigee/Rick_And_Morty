package com.google.rickandmorty.characters

import com.google.rickandmorty.GetCharacterByIdResponse

class CharactersRepository {

    suspend fun getCharacterList(pageIndex: Int): List<GetCharacterByIdResponse> {
        return emptyList()
    }
}