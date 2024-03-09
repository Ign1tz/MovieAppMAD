package com.example.movieappmad24

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector



data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val movies: List<Movie> = mutableListOf<Movie>(),
    val count: Int? = null
)

fun getNavItems(): List<BottomNavItem> {
    return listOf(
        BottomNavItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            movies = getMovies()
        ),
        BottomNavItem(
            title = "Favourites",
            selectedIcon = Icons.Filled.Star,
            unselectedIcon = Icons.Outlined.Star,
            count = 0,
            movies = mutableListOf<Movie>()
        ),
    )
}