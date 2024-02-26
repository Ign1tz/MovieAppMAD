package com.example.movieappmad24

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.zIndex
import coil.compose.rememberImagePainter
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text(text = "mMovies") })
                    },
                    bottomBar = {
                        BottomAppBar({
                            Row {
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = "Back"
                                    )
                                }
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        })
                    }) { _ -> movieList(list = getMovies()) }
            }
        }
    }


    @Composable
    fun movieList(list: List<Movie>) {
        LazyColumn {
            items(list) { movie ->
                MovieCard(movie = movie)
            }
        }
    }


    @Composable
    fun MovieCard(movie: Movie) {
        var arrowIcon = remember { mutableStateOf(Icons.Default.KeyboardArrowDown) }
        Card {
            Box {

                Image(
                    painter = rememberImagePainter(
                        data = movie.images[0]
                    ),
                    contentDescription = "Coil Image",
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(onClick = {}, modifier = Modifier.align(Alignment.TopEnd)) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Back"
                    )
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = movie.title)
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
        if (arrowIcon.value == Icons.Default.KeyboardArrowUp)
        Column{
            Text(text = "Director: " + movie.director)
            Text(text = "Released: " + movie.year)
            Text(text = "Genre: " + movie.genre)
            Text(text = "Actors: " + movie.actors)
            Text(text = "Rating: " + movie.rating)
            Divider(color = Color.Gray, thickness = 1.dp)
            Text(text = "Plot: " + movie.plot)
            Text(text= "imag " + movie.images)
        }
    }
}

