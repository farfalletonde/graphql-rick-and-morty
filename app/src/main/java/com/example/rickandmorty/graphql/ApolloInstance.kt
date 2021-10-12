package com.example.rickandmorty.graphql

import com.apollographql.apollo.ApolloClient

object ApolloInstance {
    val apolloClient = ApolloClient.builder()
        .serverUrl("https://rickandmortyapi.com/graphql")
        .build()
}