package com.example.movieappmad24.database

import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.Flow

class MovieRepo(private val movieDAO: MovieDAO) {
    suspend fun addMovie(movie: Movie) = movieDAO.insert(movie)
    //fun deleteMovie(movie: Movie) = MovieDAO.delete(movie)

    suspend fun updateMovie(movie: Movie) = movieDAO.update(movie)
    suspend fun insertImage(images: MovieImages) = movieDAO.insertImage(image = images)

    fun getAllMovies(): Flow<List<MovieWithImages>> = movieDAO.getAllMovies()
    fun getFavoriteMovies(): Flow<List<MovieWithImages>> = movieDAO.getLiked()
    fun getById(id: String?): Flow<MovieWithImages?> = movieDAO.getSpecivicMovieById(id)

    companion object {
        @Volatile
        private var instance: MovieRepo? = null
        fun getInstance(dao: MovieDAO) = instance ?: synchronized(this) {
            instance ?: MovieRepo(dao).also {
                instance = it
            }
        }
    }
}