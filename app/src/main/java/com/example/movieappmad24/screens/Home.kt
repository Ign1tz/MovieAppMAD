package com.example.movieappmad24.screens

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.example.movieappmad24.Movie
import com.example.movieappmad24.getMovies
import com.example.movieappmad24.getNavItems
import com.example.movieappmad24.likedList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    val movieList = remember { mutableStateOf(getMovies()) }
    val navItems = getNavItems()
    val selectedItemIndex = remember {
        mutableStateOf(0)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "mMovies") })
        },
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex.value == index,
                        onClick = {navController.navigate("${item.title.lowercase()}")
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex.value) item.selectedIcon
                                else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }
        }
    ) { PaddingValues ->
        GenerateMovieList(list = movieList.value, paddingValues = PaddingValues, navController)
    }

}


@Composable
fun GenerateMovieList(
    list: List<Movie>,
    paddingValues: PaddingValues,
    navController: NavController
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
            GenerateMovieCard(movie = movie, { id -> navController.navigate("details/$id") })
        }
    }
}


@Composable
fun GenerateMovieCard(movie: Movie, onItemClick: (String) -> Unit) {
    val showDescription = remember { mutableStateOf(false) }
    val liked = remember { mutableStateOf(movie in likedList) }
    val imageUrl = remember { mutableStateOf(movie.images[0]) }
    var imageIndex = 0
    liked.value = movie in likedList
    imageUrl.value = movie.images[imageIndex]
    showDescription.value = false
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
                    onItemClick(movie.id)
                }),//
                //              imageIndex = (imageIndex + 1) % movie.images.size
//                        imageUrl.value = movie.images[imageIndex]
            )
            IconButton(onClick = {
                liking(liked, movie)

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
}

fun liking(liked: MutableState<Boolean>, movie: Movie) {
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