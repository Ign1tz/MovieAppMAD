package com.example.movieappmad24.screens

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
import androidx.navigation.NavController
import com.example.movieappmad24.getMovies
import com.example.movieappmad24.getNavItems
import com.example.movieappmad24.likedList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favourits(navController: NavController) {
    val movieList = remember { mutableStateOf(likedList) }
    val navItems = getNavItems()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Favourites") })
        },
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = "Favourites" == item.title,
                        onClick = {navController.navigate("${item.title.lowercase()}")
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if ("Favourites" == item.title) item.selectedIcon
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