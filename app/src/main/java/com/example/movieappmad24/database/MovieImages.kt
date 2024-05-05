package com.example.movieappmad24.database

import androidx.room.*

@Entity
data class MovieImages(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "movieId") val movieId: String,
    @ColumnInfo(name = "url") val url: String
)
