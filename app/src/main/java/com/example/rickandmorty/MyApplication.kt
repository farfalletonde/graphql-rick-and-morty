package com.example.rickandmorty

import android.app.Application
import android.content.Context
import com.example.rickandmorty.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

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
        val context: Context = MyApplication.applicationContext()

        startKoin {
            modules(Modules.mainViewModelModule, Modules.repositoryModule, Modules.apolloClientModule)
        }
    }
}