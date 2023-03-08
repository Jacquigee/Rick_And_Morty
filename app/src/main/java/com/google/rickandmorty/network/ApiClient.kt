package com.google.rickandmorty.network


import com.google.rickandmorty.GetCharacterByIdResponse
import com.google.rickandmorty.characters.GetCharactersPageResponse
import retrofit2.Response

class ApiClient(
    private val rickMortyApiService: RickMortyApiService
) {
    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { rickMortyApiService.getCharacterById(characterId) }
    }

    suspend fun getCharactersPage(pageIndex: Int): SimpleResponse<GetCharactersPageResponse> {
        return safeApiCall { rickMortyApiService.getCharactersPage(pageIndex) }
    }
    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T>{
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}