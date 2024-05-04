package com.example.movieappmad24.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieappmad24.Databasee.MovieRepo
import com.example.movieappmad24.components.Movie
import com.example.movieappmad24.components.getMovies

class HomeViewModel(private val repo: MovieRepo): ViewModel() {

    var movieList = getMovies()

    fun liking(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
    }
}