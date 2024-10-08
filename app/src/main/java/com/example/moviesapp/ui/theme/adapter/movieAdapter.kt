package com.example.moviesapp.ui.theme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.data.model.Movie


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun submitList(movieList: List<Movie>) {
        movies = movieList
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title)
        private val overviewTextView: TextView = itemView.findViewById(R.id.overview)
        private val posterImageView: ImageView = itemView.findViewById(R.id.poster)

        fun bind(movie: Movie) {
            titleTextView.text = movie.title
            overviewTextView.text = movie.overview
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .dontAnimate().into(posterImageView)
        }
    }
}