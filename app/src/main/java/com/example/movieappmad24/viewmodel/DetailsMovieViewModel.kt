package com.example.movieappmad24.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.database.MovieRepo
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.view_models.MovieViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsMovieViewModel(val movieRepo: MovieRepo): ViewModel(), MovieViewModel {
    private val movie = MutableStateFlow<MovieWithImages?>(null)

    fun getMovieById(id: String?): StateFlow<MovieWithImages?>? {
        viewModelScope.launch {
            movieRepo.getById(id).collect {
                movie.value = it
            }
        }
        return movie.asStateFlow()
    }

    override fun updateFavourite(instance: MovieWithImages) {
        instance.movie.isFavourite = !instance.movie.isFavourite
        viewModelScope.launch {
            movieRepo.updateMovie(movie = instance.movie)
        }
    }
}