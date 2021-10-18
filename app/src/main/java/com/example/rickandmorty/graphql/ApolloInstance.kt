package com.example.rickandmorty.graphql

import com.apollographql.apollo.ApolloClient

object ApolloInstance {

    fun provideApolloInstance(): ApolloClient{
        return ApolloClient.builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }
}