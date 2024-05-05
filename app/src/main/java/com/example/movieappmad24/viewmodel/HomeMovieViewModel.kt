package com.example.movieappmad24.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.database.MovieRepo
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.view_models.MovieViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HomeMovieViewModel(val movieRepo: MovieRepo) : ViewModel(), MovieViewModel {
    private val mutalbeAllMovies = MutableStateFlow(listOf<MovieWithImages>())
    val allMovies: StateFlow<List<MovieWithImages>> = mutalbeAllMovies.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepo.getAllMovies().distinctUntilChanged().collect { movies ->
                mutalbeAllMovies.value = movies
            }
        }
    }

    override fun updateFavourite(instance: MovieWithImages) {
        instance.movie.isFavourite = !instance.movie.isFavourite
        viewModelScope.launch {
            movieRepo.updateMovie(movie = instance.movie)
        }
    }
}