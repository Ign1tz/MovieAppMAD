package com.example.movieappmad24.models

import androidx.room.*
import com.example.movieappmad24.database.Movie
import com.example.movieappmad24.database.MovieImages

data class MovieWithImages(
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val movieImages: List<MovieImages>
)