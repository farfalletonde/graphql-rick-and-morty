package com.example.rickandmorty.ui

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.example.rickandmorty.graphql.ApolloInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val apolloClient: ApolloClient): ViewModel() {

}