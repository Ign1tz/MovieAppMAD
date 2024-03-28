package com.example.movieappmad24.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.movieappmad24.components.GenerateMovieList
import com.example.movieappmad24.components.genBottomBar
import com.example.movieappmad24.components.genTopAppBar
import com.example.movieappmad24.getMovies

@Composable
fun Home(navController: NavController) {
    val movieList = remember { mutableStateOf(getMovies()) }

    Scaffold(
        topBar = {
            genTopAppBar(title = "mMovies", details = false)
        },
        bottomBar = {
            genBottomBar(navController = navController, currentIndex = 0)
        }
    ) { PaddingValues ->
        GenerateMovieList(list = movieList.value, paddingValues = PaddingValues, navController)
    }

}



