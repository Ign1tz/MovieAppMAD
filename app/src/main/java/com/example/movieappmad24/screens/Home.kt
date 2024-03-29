package com.example.movieappmad24.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.movieappmad24.components.GenerateMovieList
import com.example.movieappmad24.components.GenBottomBar
import com.example.movieappmad24.components.GenTopAppBar
import com.example.movieappmad24.getMovies

@Composable
fun Home(navController: NavController) {
    val movieList = remember { mutableStateOf(getMovies()) }

    Scaffold(
        topBar = {
            GenTopAppBar(title = "mMovies", details = false)
        },
        bottomBar = {
            GenBottomBar(navController = navController, currentIndex = 0)
        }
    ) { paddingValues ->
        GenerateMovieList(list = movieList.value, paddingValues = paddingValues, navController)
    }

}



