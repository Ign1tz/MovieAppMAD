package com.example.movieappmad24.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.navigation.Screens


@Composable
fun GenerateMovieList(
    list: List<MovieWithImages>,
    paddingValues: PaddingValues,
    navController: NavController,
    liking: (MovieWithImages) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = paddingValues.calculateBottomPadding(),
                top = paddingValues.calculateTopPadding()
            )
    ) {
        items(list) { movie ->
            GenerateMovieCard(
                movie = movie,
                { id: String -> navController.navigate(Screens.Details.route + "/${id}") },
                liking = liking
            )
        }
    }
}

@Composable
fun GenerateMovieCard(
    movie: MovieWithImages,
    onItemClick: (String) -> Unit = {},
    liking: (MovieWithImages) -> Unit = {}
) {
    val showDescription = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(all = 5.dp),
    ) {
        movieImage(movie.movieImages[0].url, onItemClick, movie, liking)
        movieDescription(showDescription, movie)
    }
}

val movieImage = @Composable
{ imageUrl: String, onItemClick: (String) -> Unit, movie: MovieWithImages, liking: (MovieWithImages) -> Unit ->

    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier.clickable(onClick = {
                onItemClick(movie.movie.id)
            }),
        )
        IconButton(onClick = {
            liking(movie)
        }, modifier = Modifier.align(Alignment.TopEnd)) {
            Icon(
                imageVector = if (movie.movie.isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                tint = if (movie.movie.isFavourite) Color.Red else Color.LightGray,
                contentDescription = "Back"
            )
        }
    }
}

val movieDescription = @Composable
{ showDescription: MutableState<Boolean>, movie: MovieWithImages ->

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { showDescription.value = !showDescription.value }),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = movie.movie.title,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
            fontSize = 20.sp
        )
        Icon(
            imageVector = if (showDescription.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = "Back",
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
    AnimatedVisibility(showDescription.value) {
        Description(movie)
    }
}


@Composable
fun Description(movie: MovieWithImages) {
    Column(modifier = Modifier.padding(all = 12.dp)) {
        Text(text = "Director: " + movie.movie.director)
        Text(text = "Released: " + movie.movie.year)
        Text(text = "Genre: " + movie.movie.genre)
        Text(text = "Actors: " + movie.movie.actors)
        Text(text = "Rating: " + movie.movie.rating)
        Divider(color = Color.Gray, thickness = 1.dp)
        Text(text = "Plot: " + movie.movie.plot)
    }
}