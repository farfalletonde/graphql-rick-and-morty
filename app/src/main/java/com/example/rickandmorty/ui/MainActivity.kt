package com.example.rickandmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.GetCharactersQuery
import com.example.rickandmorty.R
import com.example.rickandmorty.adapter.CharacterListAdapter
import com.example.rickandmorty.util.StateResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private var arrayList = ArrayList<GetCharactersQuery.Result?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.charactersRw)
        recyclerView.adapter = CharacterListAdapter(arrayList)

        viewModel.charactersData.observe(this) { state ->
            viewModel
            when (state) {

                is StateResource.Loading -> {

                }

                is StateResource.Success -> {
                    for (item in state.data) {
                        arrayList.add(item)
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                }

                is StateResource.Error -> {

                }
            }
        }

    }
}