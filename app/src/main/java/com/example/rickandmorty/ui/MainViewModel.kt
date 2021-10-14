package com.example.rickandmorty.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.GetCharactersQuery
import com.example.rickandmorty.repository.AppRepository
import com.example.rickandmorty.util.StateResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val repository: AppRepository): ViewModel() {

    private val _charactersData = MutableLiveData<StateResource<List<GetCharactersQuery.Result?>>>()
    val charactersData: LiveData<StateResource<List<GetCharactersQuery.Result?>>>
        get() = _charactersData

    init {
        getData(1, "")
    }

    fun getData(page: Int, filter: String) : LiveData<StateResource<List<GetCharactersQuery.Result?>>> {

        _charactersData.postValue(StateResource.Loading())

        viewModelScope.launch {

            _charactersData.postValue(StateResource.Loading())
            repository.getCharacters(page, filter).enqueue(object: ApolloCall.Callback<GetCharactersQuery.Data>() {

                override fun onResponse(response: Response<GetCharactersQuery.Data>) {
                    val responseData = response.data
                    if (responseData != null) {
                        _charactersData.postValue(StateResource.Success(responseData.characters!!.results!!))
                        Log.e("MainA", responseData.characters.toString())
                    }
                }

                override fun onFailure(e: ApolloException) {
                    _charactersData.postValue(StateResource.Error(e))
                }

            })
        }

        return charactersData
    }
}
