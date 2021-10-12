package com.example.rickandmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.rickandmorty.R
import com.example.rickandmorty.util.StateResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getData().observeForever {
            when (it) {

                is StateResource.Loading -> {
                    // do this when loading
                }

                is StateResource.Success -> {
                    // do this when loading is successful
                }

                is StateResource.Error -> {
                    // do this when an error occurs
                }
            }
        }
    }
}