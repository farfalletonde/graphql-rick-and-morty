package com.example.rickandmorty.di

import com.apollographql.apollo.ApolloClient
import com.example.rickandmorty.graphql.ApolloInstance
import com.example.rickandmorty.repository.AppRepository
import com.example.rickandmorty.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {

    val mainViewModelModule = module {
        viewModel {
            MainViewModel(get())
        }
    }

    val repositoryModule = module {
        single {
            AppRepository(get())
        }
    }

    val apolloClientModule = module {
        single {
            ApolloInstance.provideApolloInstance()
        }
    }
}