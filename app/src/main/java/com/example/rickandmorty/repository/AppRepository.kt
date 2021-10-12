package com.example.rickandmorty.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.example.GetCharactersQuery
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class AppRepository
@Inject
constructor(private val apolloClient: ApolloClient) {

    fun getCharacters(page: Int) = apolloClient.query(GetCharactersQuery(Input.optional(page), Input.absent()))

}