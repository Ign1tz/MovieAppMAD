package com.example.movieappmad24.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieappmad24.navigation.Screens


@Composable
fun GenerateMovieList(
    list: List<Movie>,
    paddingValues: PaddingValues,
    navController: NavController,
    movieViewModel: MovieViewModel,
    favourites: Boolean = false
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
            if (favourites) {
                if (movie.isFavorite) {
                    GenerateMovieCard(
                        movie = movie,
                        { movi -> navController.navigate(Screens.Details.route + "/${movi.id}") },
                        movieViewModel,
                        { movie -> movieViewModel.liking(movie) }
                    )
                }
            } else {
                GenerateMovieCard(
                    movie = movie,
                    { movi -> navController.navigate(Screens.Details.route + "/${movi.id}") },
                    movieViewModel,
                    { movie -> movieViewModel.liking(movie) }
                )
            }


        }
    }
}

@Composable
fun GenerateMovieCard(
    movie: Movie,
    onItemClick: (Movie) -> Unit = {},
    movieViewModel: MovieViewModel,
    liking: (Movie) -> Unit = {}
) {
    val showDescription = remember { mutableStateOf(false) }
    val imageUrl = remember { mutableStateOf(movie.images[0]) }
    Card(
        modifier = Modifier.padding(all = 5.dp),
    ) {
        movieImage(imageUrl.value, onItemClick, movie, liking)
        movieDescription(showDescription, movie)
    }
}

val movieImage = @Composable
{ imageUrl: String, onItemClick: (Movie) -> Unit, movie: Movie, liking: (Movie) -> Unit ->

    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier.clickable(onClick = {
                onItemClick(movie)
            }),
        )
        IconButton(onClick = {
            liking(movie)
        }, modifier = Modifier.align(Alignment.TopEnd)) {
            Icon(
                imageVector = if (movie.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                tint = if (movie.isFavorite) Color.Red else Color.LightGray,
                contentDescription = "Back"
            )
        }
    }
}

val movieDescription = @Composable
{ showDescription: MutableState<Boolean>, movie: Movie ->
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { showDescription.value = !showDescription.value }),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = movie.title,
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
fun Description(movie: Movie) {
    Column(modifier = Modifier.padding(all = 12.dp)) {
        Text(text = "Director: " + movie.director)
        Text(text = "Released: " + movie.year)
        Text(text = "Genre: " + movie.genre)
        Text(text = "Actors: " + movie.actors)
        Text(text = "Rating: " + movie.rating)
        Divider(color = Color.Gray, thickness = 1.dp)
        Text(text = "Plot: " + movie.plot)
    }
}