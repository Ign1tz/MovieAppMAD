package com.example.movieappmad24.Databasee

import androidx.room.*

@Entity
data class Movie(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "genre") val genre: String,
    @ColumnInfo(name = "director") val director: String,
    @ColumnInfo(name = "actors") val actors: String,
    @ColumnInfo(name = "plot") val plot: String,
    //@ColumnInfo(name = "images") val images: MovieImages,
    @ColumnInfo(name = "trailer") val trailer: String,
    @ColumnInfo(name = "rating") val rating: String,
    @ColumnInfo(name = "favourite") val favourite: Boolean
)
