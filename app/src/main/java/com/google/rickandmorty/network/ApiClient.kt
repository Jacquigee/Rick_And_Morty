package com.google.rickandmorty.network


import com.google.rickandmorty.GetCharacterByIdResponse
import retrofit2.Response

class ApiClient(
    private val rickMortyApiService: RickMortyApiService
) {
    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { rickMortyApiService.getCharacterById(characterId) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T>{
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}