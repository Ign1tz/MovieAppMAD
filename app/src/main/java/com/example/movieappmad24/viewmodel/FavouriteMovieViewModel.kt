package com.example.movieappmad24.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieappmad24.components.Movie

class FavouriteMovieViewModel: ViewModel() {
    var favoriteList = mutableListOf<Movie>()

    fun liking(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
    }
}