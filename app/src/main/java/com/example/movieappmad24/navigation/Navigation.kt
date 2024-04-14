package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.components.MovieViewModel
import com.example.movieappmad24.components.getMovieById
import com.example.movieappmad24.screens.Details
import com.example.movieappmad24.screens.Favourites
import com.example.movieappmad24.screens.Home

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val test: MovieViewModel = viewModel()
    val list = test.movieList
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            Home(navController, test, list)
        }
        composable(
            route = Screens.Details.route+"/{movieid}",
            arguments = listOf(navArgument(name = "movieid") { type = NavType.StringType })
        ) { backStackEntry ->
            Details(movie = getMovieById(backStackEntry.arguments?.getString("movieid"), test), navController = navController, test)
        }
        composable(
            route = Screens.Favourites.route
        ) {
            Favourites(navController = navController, test, list)
        }
    }
}