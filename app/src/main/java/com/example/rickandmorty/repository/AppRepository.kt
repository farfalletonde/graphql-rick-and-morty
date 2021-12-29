package com.example.rickandmorty.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Input
import com.example.GetCharactersQuery

class AppRepository
constructor(private val apolloClient: ApolloClient) {

    fun getCharacters(page: Int, filter: String? = null): ApolloQueryCall<GetCharactersQuery.Data> = apolloClient.query(GetCharactersQuery(Input.optional(page), Input.optional(filter)))

}