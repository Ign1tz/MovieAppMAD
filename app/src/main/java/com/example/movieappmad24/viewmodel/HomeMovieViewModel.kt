package com.example.movieappmad24.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.Databasee.MovieImages
import com.example.movieappmad24.Databasee.MovieRepo
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.models.getMovies
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
            /*movieRepo.getAllMovies().distinctUntilChanged().collect { movies ->
                if (movies.isEmpty()) {
                    movieRepo.deleteAllMovie()
                    movieRepo.deleteAllImages()
                    getMovies().forEach { movie: Movie ->
                        movie.images.forEach {
                            movieRepo.insertImage(MovieImages(movieId = movie.id, url = it))
                        }
                        movieRepo.addMovie(movie)
                    }
                    movieRepo.getAllMovies().distinctUntilChanged().collect { movies ->
                        mutalbeAllMovies.value = movies
                    }
                } else {
                    mutalbeAllMovies.value = movies
                }
            }*/
            movieRepo.getAllMovies().distinctUntilChanged().collect { movies ->
                mutalbeAllMovies.value = movies
            }
        }

    }

    override fun updateFavourite(instance: MovieWithImages) {
        instance.movie.isFavourite = !instance.movie.isFavourite
        viewModelScope.launch {
            Log.d("test", "testing home")
            movieRepo.updateMovie(movie = instance.movie)
        }
    }
}