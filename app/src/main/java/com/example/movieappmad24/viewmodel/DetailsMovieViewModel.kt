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
import kotlinx.coroutines.launch

class DetailsMovieViewModel(val movieRepo: MovieRepo): ViewModel(), MovieViewModel {
    private val movie: MutableStateFlow<MovieWithImages?>? = null

    fun getMovieById(id: String?): StateFlow<MovieWithImages?>? {
        var newMovie: MutableStateFlow<MovieWithImages?>? = null
        viewModelScope.launch {
            Log.d("testing", id?:"null")
            movieRepo.getById(id).collect { movie ->
                Log.d("testing return", movie.toString())
                newMovie?.value = movie
                this@DetailsMovieViewModel.movie?.value = movie
            }
        }
        Log.d("testing new movie", newMovie.toString())
        return movie?.asStateFlow()
    }

    override fun updateFavourite(instance: MovieWithImages) {
        instance.movie.isFavourite = !instance.movie.isFavourite
        viewModelScope.launch {
            movieRepo.updateMovie(movie = instance.movie)
        }
    }
}