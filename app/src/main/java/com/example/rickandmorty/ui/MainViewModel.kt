package com.example.rickandmorty.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.coroutines.await
import com.example.GetCharactersQuery
import com.example.rickandmorty.repository.AppRepository
import com.example.rickandmorty.util.StateResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel
constructor(private val repository: AppRepository): ViewModel() {

    private val _charactersData = MutableLiveData<StateResource<List<GetCharactersQuery.Result?>>>()
    val charactersData: LiveData<StateResource<List<GetCharactersQuery.Result?>>>
        get() = _charactersData

    init {
        getData(1)
    }

    fun getData(page: Int, filter: String? = null) : LiveData<StateResource<List<GetCharactersQuery.Result?>>> {

        _charactersData.postValue(StateResource.Loading())

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _charactersData.postValue(
                    StateResource.Success(
                        repository.getCharacters(
                            page,
                            filter
                        ).await().data?.characters?.results!!
                    )
                )
            } catch (e: Exception) {
                _charactersData.postValue(StateResource.Error(e))
            }
        }

        return charactersData
    }
}
