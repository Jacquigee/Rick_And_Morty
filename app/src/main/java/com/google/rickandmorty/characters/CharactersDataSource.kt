package com.google.rickandmorty.characters

import androidx.paging.PageKeyedDataSource
import com.google.rickandmorty.GetCharacterByIdResponse
import com.google.rickandmorty.viewModel.SharedRepository
import com.google.rickandmorty.viewModel.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CharactersDataSource(
    private val coroutineScope: CoroutineScope,
    private val repository: CharactersRepository
): PageKeyedDataSource<Int, GetCharacterByIdResponse>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GetCharacterByIdResponse>
    ) {
        coroutineScope.launch {
            val characterList = repository.getCharacterList(1)
            callback.onResult(characterList, null, 2)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GetCharacterByIdResponse>
    ) {
        //nothing to do so far
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GetCharacterByIdResponse>
    ) {
        coroutineScope.launch {
            val characterList = repository.getCharacterList(params.key)
            callback.onResult(characterList, params.key + 1)
        }
    }

}