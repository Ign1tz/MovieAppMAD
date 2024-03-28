package com.example.movieappmad24.screens;

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.Movie
import com.example.movieappmad24.components.GenerateMovieCard
import com.example.movieappmad24.components.genTopAppBar


@Composable
fun Details(movie: Movie, navController: NavController) {
    Scaffold(
        topBar = {
            genTopAppBar(title = movie.title, details = true, navController = navController)
        }
    ) { PaddingValues ->
        GenerateMovieDetails(movie = movie, paddingValues = PaddingValues)
    }
}


@Composable
fun GenerateMovieDetails(movie: Movie, paddingValues: PaddingValues) {
    Column(modifier = Modifier.padding(paddingValues)) {
        GenerateMovieCard(movie = movie)
        LazyRow {
            items(movie.images) { imageURL ->
                AsyncImage(
                    model = imageURL, contentDescription = "null"
                )
            }
        }
    }
}

