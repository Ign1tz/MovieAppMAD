package com.example.movieappmad24.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieappmad24.components.Movie

class DetailsMovieViewModel: ViewModel() {
    var currentMovie: Movie? = null

    fun liking(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
    }
}