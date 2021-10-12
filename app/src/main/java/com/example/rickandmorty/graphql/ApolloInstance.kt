package com.example.rickandmorty.graphql

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApolloInstance {

    @Singleton
    @Provides
    fun provideApolloInstance(): ApolloClient{
        return ApolloClient.builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .build()
    }
}