package com.example.rickandmorty

import android.app.Application
import android.content.Context
import com.example.rickandmorty.di.Modules
import org.koin.core.context.startKoin

class MyApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(Modules.mainViewModelModule, Modules.repositoryModule, Modules.apolloClientModule)
        }
    }
}