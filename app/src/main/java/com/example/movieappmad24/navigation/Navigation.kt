package com.example.movieappmad24.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.movieappmad24.screens.Details
import com.example.movieappmad24.screens.Favourites
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
            Details(navController = navController, id = backStackEntry.arguments?.getString(DETAILS_KEY))
        }
        composable(
            route = Screens.Favourites.route
        ) {
            Favourites(navController = navController)
        }
    }
}