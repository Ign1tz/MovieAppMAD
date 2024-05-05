package com.example.movieappmad24.screens

import android.util.Log
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
import com.example.movieappmad24.viewmodel.FavouriteMovieViewModel
import com.example.movieappmad24.viewmodel.HomeMovieViewModel

@Composable
fun Favourites(
    navController: NavController
) {
    val viewModel: FavouriteMovieViewModel = viewModel(factory = Injector.provideMovieViewModelFactory(context = LocalContext.current))
    Scaffold(
        topBar = {
            GenTopAppBar(title = "mMovies", details = false)
        },
        bottomBar = {
            GenBottomBar(navController = navController, currentIndex = 1)
        }
    ) { paddingValues ->
        GenerateMovieList(
            list = viewModel.favourites.collectAsState().value,
            paddingValues = paddingValues,
            navController,
            viewModel = viewModel
        )
    }
}