package com.google.rickandmorty.characters

import com.google.rickandmorty.GetCharacterByIdResponse

data class GetCharactersPageResponse(
    val info: Info,
    val results: List<GetCharacterByIdResponse> = emptyList()
)