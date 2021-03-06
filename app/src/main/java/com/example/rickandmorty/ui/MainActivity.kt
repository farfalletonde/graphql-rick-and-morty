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

    private val viewModel by viewModel<MainViewModel>()
    private var arrayList = ArrayList<GetCharactersQuery.Result?>()

    private var page = 1
    private var filter: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            charactersRw.adapter = CharacterListAdapter(arrayList)

            viewModel.charactersData.observe(this@MainActivity) { state ->

                when (state) {

                    is StateResource.Loading -> { }

                    is StateResource.Success -> {
                        state.data.forEach {
                            arrayList.add(it)
                        }
                        charactersRw.adapter?.notifyDataSetChanged()
                    }

                    is StateResource.Error -> {
                        when (state.e) {
                            is NullPointerException -> {
                                Toast.makeText(this@MainActivity, "No more data were found", Toast.LENGTH_SHORT).show()
                            }
                            is ApolloNetworkException -> {
                                nextPageButton.text = "No internet connection"
                            }
                        }
                    }
                }
            }

            //handles filter operations
            filterButton.setOnClickListener {
                val popupMenu = PopupMenu(this@MainActivity , it)

                popupMenu.menuInflater.inflate(R.menu.filter_menu, popupMenu.menu)

                popupMenu.setOnMenuItemClickListener { menuItem ->
                    arrayList.removeAll(arrayList)
                    filter = menuItem.title.toString()
                    page = 1
                    viewModel.getData(page, menuItem.title.toString())
                    true
                }
                popupMenu.show()
            }


            //next page check
            nextPageButton.setOnClickListener {
                page++
                getPage(page, filter)
            }
        }
    }

    private fun getPage(page: Int, filter: String? = null) {
        viewModel.getData(page, filter)
    }
}