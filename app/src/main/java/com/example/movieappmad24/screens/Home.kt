package com.example.movieappmad24.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappmad24.components.GenerateMovieList
import com.example.movieappmad24.components.GenBottomBar
import com.example.movieappmad24.components.GenTopAppBar
import com.example.movieappmad24.dependency_injection.Injector
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.viewmodel.HomeMovieViewModel

@Composable
fun Home(
    navController: NavController
) {
    val viewModel: HomeMovieViewModel = viewModel(factory = Injector.provideMovieViewModelFactory(context = LocalContext.current))
    Scaffold(
        topBar = {
            GenTopAppBar(title = "mMovies", details = false)
        },
        bottomBar = {
            GenBottomBar(navController = navController, currentIndex = 0)
        }
    ) { paddingValues ->
        GenerateMovieList(list = viewModel.allMovies.collectAsState().value, paddingValues = paddingValues, navController,
            { movie: MovieWithImages -> viewModel.updateFavourite(movie) }
        )
    }

}



