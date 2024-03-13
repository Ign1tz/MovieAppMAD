package com.example.movieappmad24.screens;

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.request.ImageRequest
import com.example.movieappmad24.Movie
import com.example.movieappmad24.getMovies
import com.example.movieappmad24.getNavItems
import com.example.movieappmad24.screens.GenerateMovieCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(movie: Movie, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = movie.title) },
                navigationIcon =
                {IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }}
            )
        }
    ) { PaddingValues ->
        GenerateMovieDetails(movie = movie, paddingValues = PaddingValues)
    }
}


@Composable
fun GenerateMovieDetails(movie: Movie, paddingValues: PaddingValues) {
    Column(modifier = Modifier.padding(paddingValues)) {
        GenerateMovieCard(movie = movie, {})
        LazyRow() {
            items(movie.images) { imageURL ->
                AsyncImage(
                    model = imageURL, contentDescription = "null"
                )
            }
        }
    }
}
