package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular?api_key=0c44ec8e26aea5702eb3cb2e20f8938d&page=1")
    fun getPopularMovies(
//        @Query("api_key") apiKey: String,
//        @Query("page") page: Int
    ): Observable<MovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Observable<MovieResponse>

    @GET("search/movie")
    fun getSearchedMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("include_adult") includeAdult: Boolean
    ): Observable<MovieResponse>
}