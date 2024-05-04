package com.example.movieappmad24.Databasee

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Insert
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Update()
    fun update(movie: Movie)

    @Query("SELECT * from Movie")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT * FROM Movie where 'favourite' = true")
    fun getLiked(): Flow<List<Movie>>

    @Query("SELECT * FROM Movie where 'id' = :id")
    fun getSpecivicMovie(id: String): Flow<Movie?>
}