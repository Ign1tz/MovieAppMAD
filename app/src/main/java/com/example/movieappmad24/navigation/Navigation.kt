package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.getMovieById
import com.example.movieappmad24.screens.Details
import com.example.movieappmad24.screens.Favourits
import com.example.movieappmad24.screens.Home

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            Home(navController)
        }
        composable(
            route = Screens.Details.route+"/{movieid}",
            arguments = listOf(navArgument(name = "movieid") { type = NavType.StringType })
        ) { backStackEntry ->
            Details(movie = getMovieById(backStackEntry.arguments?.getString("movieid")), navController = navController)
        }
        composable(
            route = Screens.Favourites.route
        ) {
            Favourits(navController = navController)
        }
    }
}