package com.example.rickandmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.GetCharactersQuery
import com.example.rickandmorty.R
import com.example.rickandmorty.adapter.CharacterListAdapter
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.util.StateResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private var arrayList = ArrayList<GetCharactersQuery.Result?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        recyclerView = binding.charactersRw
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

        filterMenuOperations()

    }

    fun filterMenuOperations() {
        val filterButton = binding.filterButton
        filterButton.setOnClickListener {
            val popupMenu = PopupMenu(this , it)

            popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { menuItem ->
                arrayList.removeAll(arrayList)
                viewModel.getData(1, menuItem.title.toString())
                return@OnMenuItemClickListener true
            })
            popupMenu.show()
        }
    }
}