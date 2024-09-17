package com.example.moviesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.ui.theme.adapter.MoviesAdapter
import com.example.moviesapp.ui.theme.view.MyBottomSheetFragment
import com.example.moviesapp.ui.theme.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MoviesAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val button = findViewById<Button>(R.id.button)

        val filterIcon = findViewById<ImageView>(R.id.filter_icon)


        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter

        viewModel.movies.observe(this) { movies ->
            adapter.submitList(movies)
        }
        viewModel.fetchPopularMovies("0c44ec8e26aea5702eb3cb2e20f8938d", 1)

        button.setOnClickListener {
            val searchBar = findViewById<EditText>(R.id.search_bar)
            val searchText = searchBar.text.toString()
            println("hello ${searchText}")
//            viewModel.fetchNowPlayingMovies("0c44ec8e26aea5702eb3cb2e20f8938d", 1)
            viewModel.fetchSearchedMovies(searchText, "0c44ec8e26aea5702eb3cb2e20f8938d", false)

        }

        filterIcon.setOnClickListener {
//            viewModel.sortMovieAscending()
            val bottomSheetFragment = MyBottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

    }
}