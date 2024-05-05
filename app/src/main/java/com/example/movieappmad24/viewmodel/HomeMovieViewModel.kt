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
            movieRepo.deleteAllMovie()
            movieRepo.deleteAllImages()
            getMovies().forEach { movie: Movie ->
                //var images = mutableListOf<MovieImages>()
                movie.images.forEach {
                    movieRepo.insertAllImages(MovieImages(movieId = movie.id, url = it))
                    //images.add(MovieImages(movieId = movie.id, url = it))
                }
                movieRepo.addMovie(movie)
            }
        }
        viewModelScope.launch {
            movieRepo.getAllMovies().distinctUntilChanged().collect {
                    movies -> mutalbeAllMovies.value = movies
            }
            /*var mutmovies = mutableListOf<MovieWithImages>()
            movieRepo.getAllMovies().distinctUntilChanged().collect { movies ->

                Log.d("testing", movies.toString())
                movies.forEach {movie: Movie ->
                    var mutImages = mutableListOf<MovieImages>()
                    movieRepo.getAllImages(movie.id).distinctUntilChanged().collect {
                        it.forEach {
                            mutImages.add(it)
                        }
                    }

                    Log.d("testing", "2")
                    mutmovies.add(MovieWithImages(movie, mutImages))
                }
            }

            Log.d("testing bevor", mutmovies.toString())
            mutalbeAllMovies.value = mutmovies
            Log.d("testing", mutmovies.toString())*/
        }
    }

    override fun updateFavourite(instance: MovieWithImages) {
        instance.movie.isFavourite = !instance.movie.isFavourite
        viewModelScope.launch {
            movieRepo.updateMovie(movie = instance.movie)
        }
    }
}