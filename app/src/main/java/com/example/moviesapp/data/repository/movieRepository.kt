package com.example.moviesapp.data.repository

import com.example.moviesapp.data.api.RetrofitClient
import com.example.moviesapp.data.model.MovieResponse
import io.reactivex.Observable

class MovieRepository {
    private val api = RetrofitClient.instance

    fun getPopularMovies(): Observable<MovieResponse> {
        return api.getPopularMovies()
    }

    fun getNowPlayingMovies(): Observable<MovieResponse> {
        return api.getNowPlayingMovies("0c44ec8e26aea5702eb3cb2e20f8938d", 1)
    }

    fun getSearchedMovies(movieName:String,includeAdult:Boolean): Observable<MovieResponse> {
        return api.getSearchedMovies(movieName,"0c44ec8e26aea5702eb3cb2e20f8938d",includeAdult)
    }
}