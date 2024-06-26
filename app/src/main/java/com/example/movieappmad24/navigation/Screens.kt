package com.example.movieappmad24.navigation

const val DETAILS_KEY = "movieid"

sealed class Screens(val route: String) {
    data object Home: Screens("home")
    data object Details: Screens("details")
    data object Favourites: Screens("favourites")
}