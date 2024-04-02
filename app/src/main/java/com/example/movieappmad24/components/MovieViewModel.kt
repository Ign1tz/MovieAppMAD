package com.example.movieappmad24.components

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.movieappmad24.Movie
import com.example.movieappmad24.getMovies

class MovieViewModel: ViewModel() {
    var movieList = getMovies()
    var favoriteList = mutableListOf<Movie>()

    fun liking(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
    }
}