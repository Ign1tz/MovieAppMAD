package com.example.movieappmad24.components

import androidx.lifecycle.ViewModel

class MovieViewModel: ViewModel() {
    var movieList = getMovies()
    var favoriteList = mutableListOf<Movie>()

    fun liking(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
    }
}