package com.example.movieappmad24.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappmad24.Movie
import com.example.movieappmad24.components.MovieViewModel
import com.example.movieappmad24.components.GenerateMovieList
import com.example.movieappmad24.components.GenBottomBar
import com.example.movieappmad24.components.GenTopAppBar

@Composable
fun Home(
    navController: NavController, test: MovieViewModel,
    list: List<Movie>,
) {
    Scaffold(
        topBar = {
            GenTopAppBar(title = "mMovies", details = false)
        },
        bottomBar = {
            GenBottomBar(navController = navController, currentIndex = 0)
        }
    ) { paddingValues ->
        GenerateMovieList(list = list, paddingValues = paddingValues, navController, test)
    }

}



