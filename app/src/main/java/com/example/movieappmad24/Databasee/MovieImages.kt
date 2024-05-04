package com.example.movieappmad24.Databasee

import androidx.room.*

@Entity
data class MovieImages(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "movieId") val movieId: String,
    @ColumnInfo(name = "url") val url: String
)
