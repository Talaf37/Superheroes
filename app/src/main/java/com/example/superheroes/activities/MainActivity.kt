package com.example.superheroes.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroes.R
import com.example.superheroes.adapter.SuperheroAdapter
import com.example.superheroes.data.SuperheroesServiceApi
import com.example.superheroes.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.searchButton.setOnClickListener {
                val searchText = binding.searchEditText.text.toString()
                searchSuperheroes(searchText)
            }*/

        adapter = SuperheroAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        initSearchView(menu?.findItem(R.id.Menu_Search))
        return true
    }

    private fun initSearchView(SearchItem: MenuItem?) {
        if (SearchItem != null) {
            var SearchView = SearchItem.actionView as SearchView

            SearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchSuperheroes(query!!)
                    return true
                }
                override fun onQueryTextChange(query: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun searchSuperheroes(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: SuperheroesServiceApi = retrofit.create(SuperheroesServiceApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val response = service.searchByName(query)

            runOnUiThread {
                // Modificar UI
                if (response.body() != null) {
                    Log.i("HTTP", "respuesta correcta :)")
                    adapter.updateItems(response.body()?.results)
                } else {
                    Log.i("HTTP", "respuesta erronea :(")
                }
            }
        }
    }
}