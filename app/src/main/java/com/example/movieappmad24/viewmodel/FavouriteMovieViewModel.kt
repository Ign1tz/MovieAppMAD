package com.example.movieappmad24.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.Databasee.MovieRepo
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.view_models.MovieViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class FavouriteMovieViewModel(val movieRepo: MovieRepo) : ViewModel(), MovieViewModel {
    private val _favourites = MutableStateFlow(listOf<MovieWithImages>())
    val favourites: StateFlow<List<MovieWithImages>> = _favourites.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepo.getFavoriteMovies().distinctUntilChanged().collect { movies ->
                _favourites.value = movies
            }
        }
    }

    override fun updateFavourite(instance: MovieWithImages) {
        instance.movie.isFavourite = !instance.movie.isFavourite
        viewModelScope.launch {
            Log.d("test", "tesing")
            movieRepo.updateMovie(movie = instance.movie)
        }
    }
}