package com.example.movieappmad24.screens

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappmad24.Movie
import com.example.movieappmad24.components.GenerateMovieList
import com.example.movieappmad24.components.GenBottomBar
import com.example.movieappmad24.components.GenTopAppBar
import com.example.movieappmad24.components.MovieViewModel

@Composable
fun Favourites(
    navController: NavController, test: MovieViewModel,
    list: List<Movie>,
) {
    Log.d("fav", test.favoriteList.toString())
    Scaffold(
        topBar = {
            GenTopAppBar(title = "mMovies", details = false)
        },
        bottomBar = {
            GenBottomBar(navController = navController, currentIndex = 1)
        }
    ) { paddingValues ->
        GenerateMovieList(
            list = list,
            paddingValues = paddingValues,
            navController,
            test,
            favourites = true
        )
    }
}