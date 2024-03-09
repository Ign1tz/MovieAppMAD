package com.example.movieappmad24

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    var likedList = mutableListOf<Movie>()

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                // A surface container using the 'background' color from the theme
                val movieList = remember {
                    mutableStateOf(getMovies())
                }
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text(text = "mMovies") })
                    },
                    bottomBar = {
                        BottomAppBar({
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    IconButton(
                                        onClick = { movieList.value = getMovies() }) {
                                        Icon(
                                            imageVector = Icons.Default.Home,
                                            contentDescription = "Back"
                                        )
                                    }
                                    Text(
                                        text = "Home",
                                        fontSize = 12.sp
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    IconButton(onClick = { movieList.value = likedList }) {
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = "Back"
                                        )
                                    }
                                    Text(
                                        text = "Favourites",
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        })
                    }) { paddingValues ->
                    generateMovieList(list = movieList.value, paddingValues = paddingValues)
                }
            }
        }
    }

    @Composable
    fun generateMovieList(list: List<Movie>, paddingValues: PaddingValues) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = paddingValues.calculateBottomPadding(),
                    top = paddingValues.calculateTopPadding()
                )
        ) {
            items(list) { movie ->
                generateMovieCard(movie = movie)
            }
        }
    }


    @Composable
    fun generateMovieCard(movie: Movie) {
        val arrow = remember { mutableStateOf(false) }
        val liked =
            remember { mutableStateOf(movie in likedList) }
        val imageUrl = remember { mutableStateOf(movie.images[0]) }
        var imageIndex = 0
        liked.value = movie in likedList
        imageUrl.value = movie.images[imageIndex]
        arrow.value = false
        Card(
            modifier = Modifier.padding(all = 5.dp),
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl.value)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.clickable(onClick = {
                        imageIndex = (imageIndex + 1) % movie.images.size
                        imageUrl.value = movie.images[imageIndex]
                    }),
                )
                IconButton(onClick = {
                    if (liked.value) {
                        liked.value = false
                        if (movie in likedList) {
                            likedList.remove(movie)
                        }
                    } else {
                        liked.value = true
                        if (movie !in likedList) {
                            likedList.add(movie)
                        }
                    }
                }, modifier = Modifier.align(Alignment.TopEnd)) {
                    Icon(
                        imageVector = if (liked.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        tint = if (liked.value) Color.Red else Color.LightGray,
                        contentDescription = "Back"
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movie.title,
                    modifier = Modifier.padding(horizontal = 5.dp),
                    fontSize = 20.sp
                )
                IconButton(onClick = {
                    arrow.value = !arrow.value
                }) {
                    Icon(
                        imageVector = if (arrow.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Back"
                    )
                }
            }
            AnimatedVisibility(arrow.value) {
                Column(modifier = Modifier.padding(all = 10.dp)) {
                    Text(text = "Director: " + movie.director)
                    Text(text = "Released: " + movie.year)
                    Text(text = "Genre: " + movie.genre)
                    Text(text = "Actors: " + movie.actors)
                    Text(text = "Rating: " + movie.rating)
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Text(text = "Plot: " + movie.plot)
                }
            }
        }

    }
}

