package com.example.rickandmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.exception.ApolloNetworkException
import com.example.GetCharactersQuery
import com.example.rickandmorty.R
import com.example.rickandmorty.adapter.CharacterListAdapter
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.util.StateResource
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var recyclerView: RecyclerView
    private var arrayList = ArrayList<GetCharactersQuery.Result?>()

    private var page = 1
    private var filter = ""

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
                    for (item in state.data)
                        arrayList.add(item)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }

                is StateResource.Error -> {
                    when (state.e) {
                        is NullPointerException -> {
                            Toast.makeText(this, "No more data were found", Toast.LENGTH_SHORT).show()
                        }
                        is ApolloNetworkException -> {
                            binding.nextPageButton.text = "No internet connection"
                        }
                    }
                }
            }
        }

        //handles filter operations
        val filterButton = binding.filterButton
        filterButton.setOnClickListener {
            val popupMenu = PopupMenu(this , it)

            popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { menuItem ->
                arrayList.removeAll(arrayList)
                filter = menuItem.title.toString()
                page = 1
                viewModel.getData(page, menuItem.title.toString())
                return@OnMenuItemClickListener true
            })
            popupMenu.show()
        }


        //next page check
        binding.nextPageButton.setOnClickListener {
            page++
            getPage(page, filter)
        }
    }

    fun getPage(page: Int, filter: String = "") {
        viewModel.getData(page, filter)
    }
}