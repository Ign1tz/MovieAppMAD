package com.example.movieappmad24.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.Databasee.MovieRepo
import com.example.movieappmad24.viewmodel.DetailsMovieViewModel
import com.example.movieappmad24.viewmodel.FavouriteMovieViewModel
import com.example.movieappmad24.viewmodel.HomeMovieViewModel

class MovieViewModelFactory(private val movieRepo: MovieRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(model: Class<T>): T =
        when (model) {
            HomeMovieViewModel::class.java -> HomeMovieViewModel(movieRepo = movieRepo)
            FavouriteMovieViewModel::class.java -> FavouriteMovieViewModel(movieRepo = movieRepo)
            DetailsMovieViewModel::class.java -> DetailsMovieViewModel(movieRepo = movieRepo)
            else -> throw IllegalArgumentException("You f**ked up, Unknown class!")
        } as T

}