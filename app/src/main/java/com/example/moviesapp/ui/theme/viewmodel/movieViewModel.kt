package com.example.moviesapp.ui.theme.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()
    private val disposable = CompositeDisposable()


    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies
    private var tempMovieList: List<Movie> = listOf()


    fun fetchPopularMovies(apiKey: String, page: Int) {
        disposable.add(
            repository.getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    tempMovieList = response.results
                    _movies.value = response.results
                    Log.d("API_RESPONSE", "Movies fetched: ${response.results.size}")
                }, { error ->
                    // handle error
                    Log.e("API_ERROR", "Error: ${error.message}")
                    println("err")
                })
        )
    }

    fun fetchNowPlayingMovies(apiKey: String, page: Int) {
        disposable.add(
            repository.getNowPlayingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    tempMovieList = response.results
                    _movies.value = response.results
                }, { error ->
                    // handle error
                    Log.e("API_ERROR", "Error: ${error.message}")
                    println("err")
                })
        )
    }

    fun fetchSearchedMovies(movieName: String, apiKey: String, include_adult: Boolean) {
        disposable.add(
            repository.getSearchedMovies(movieName, include_adult)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    tempMovieList = response.results
                    _movies.value = response.results
                    Log.d("API_RESPONSE", "Movies fetched: ${response.results.size}")
                }, { error ->
                    // handle error
                    Log.e("API_ERROR", "Error: ${error.message}")
                    println("err")
                })
        )
    }

    fun sortMovieAscending() {
        // Ensure the tempMovieList is not empty
        if (tempMovieList.isNotEmpty()) {
            // Sort by title in ascending order
            _movies.value = tempMovieList.sortedBy { it.title }
        } else {
            Log.e("SORT_ERROR", "Movie list is empty. Cannot sort.")
        }
    }

    fun sortMovieDescending() {
        // Ensure the tempMovieList is not empty
        if (tempMovieList.isNotEmpty()) {
            // Sort by title in ascending order
            _movies.value = tempMovieList.sortedByDescending { it.title }
        } else {
            Log.e("SORT_ERROR", "Movie list is empty. Cannot sort.")
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}