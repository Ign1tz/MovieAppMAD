package com.example.movieappmad24

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    var likedList = mutableListOf<Movie>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                // A surface container using the 'background' color from the theme
                page()
            }
        }
    }


    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun page() {
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
                                //modifier = Modifier.fillMaxWidth(.33f),
                                text = "Favourites",
                                fontSize = 12.sp
                            )
                        }
                    }
                })
            }) { paddingValues ->
            movieList(list = movieList.value, paddingValues = paddingValues)
        }
    }

    @Composable
    fun movieList(list: List<Movie>, paddingValues: PaddingValues) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = paddingValues.calculateBottomPadding(),
                    top = paddingValues.calculateTopPadding()
                )
        ) {
            items(list) { movie ->
                MovieCard(movie = movie)
            }
        }
    }


    @Composable
    fun MovieCard(movie: Movie) {
        val arrowIcon = remember { mutableStateOf(Icons.Default.KeyboardArrowDown) }
        val liked = remember { mutableStateOf(if (movie !in likedList) Icons.Default.FavoriteBorder else Icons.Default.Favorite) }
        Log.d("liking", (movie in likedList).toString())
        Card(
            modifier = Modifier.padding(all = 5.dp),
        ) {
            Box {
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = null,
                )
                IconButton(onClick = {
                    if (liked.value == Icons.Default.FavoriteBorder && movie !in likedList) {
                        liked.value = Icons.Default.Favorite
                        if (movie !in likedList) {
                            likedList.add(movie)
                        }
                        Log.d("likedList", likedList.toString())
                    } else {
                        liked.value = Icons.Default.FavoriteBorder
                        if (movie in likedList) {
                            likedList.remove(movie)
                        }
                        Log.d("likedList", likedList.toString())
                    }
                }, modifier = Modifier.align(Alignment.TopEnd)) {
                    Icon(
                        imageVector = liked.value,
                        tint = if (liked.value == Icons.Default.FavoriteBorder) Color.LightGray else Color.Red,
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
                    if (arrowIcon.value == Icons.Default.KeyboardArrowDown) arrowIcon.value =
                        Icons.Default.KeyboardArrowUp else arrowIcon.value =
                        Icons.Default.KeyboardArrowDown
                }) {
                    Icon(
                        imageVector = arrowIcon.value,
                        contentDescription = "Back"
                    )
                }
            }
            if (arrowIcon.value == Icons.Default.KeyboardArrowUp) {
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

