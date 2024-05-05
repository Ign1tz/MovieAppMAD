package com.example.movieappmad24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.media3.exoplayer.ExoPlayer
import com.example.movieappmad24.Databasee.MovieDAO
import com.example.movieappmad24.Databasee.MovieDatabase
import com.example.movieappmad24.Databasee.MovieRepo
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.Navigation
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                Navigation()
            }
        }
    }

}

