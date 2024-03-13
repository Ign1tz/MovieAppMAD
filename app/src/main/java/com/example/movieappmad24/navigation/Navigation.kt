package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.getMovieById
import com.example.movieappmad24.getMovies
import com.example.movieappmad24.screens.Details
import com.example.movieappmad24.screens.Favourits
import com.example.movieappmad24.screens.Home

@Composable
fun Navigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            Home(navController)
        }
        composable(
            route = "details/{movieid}",
            arguments = listOf(navArgument(name = "movieid") { type = NavType.StringType })
        ) { backStackEntry ->
            Details(movie = getMovieById(backStackEntry.arguments?.getString("movieid")), navController = navController)
        }
        composable(
            route = "favourites"
        ) {
            Favourits(navController = navController)
        }
    }
}