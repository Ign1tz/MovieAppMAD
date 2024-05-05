package com.example.movieappmad24.database

import androidx.room.*
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Insert
    suspend fun insert(movie: Movie)
    @Insert
    suspend fun insertImage(image: MovieImages)

    @Insert
    fun insertMultipleMovies(movies: List<Movie>)

    @Insert
    fun insertMultipleImages(images: List<MovieImages>)

    @Delete
    fun delete(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Query("DELETE FROM Movie")
    suspend fun deleteAll()

    @Query("DELETE FROM MovieImages")
    suspend fun deleteAllImages()
    @Query("SELECT * from Movie")
    fun getAllMovies(): Flow<List<MovieWithImages>>

    @Query("SELECT * from MovieImages where MovieImages.movieId = :id")
    fun getAllImages(id: String?): Flow<List<MovieImages>>

    @Query("SELECT * FROM Movie where isFavourite = 1")
    fun getLiked(): Flow<List<MovieWithImages>>

    @Query("SELECT * FROM Movie where id = :id")
    fun getSpecivicMovieById(id: String?): Flow<MovieWithImages?>

}