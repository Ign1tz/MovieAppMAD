package com.example.movieappmad24.Databasee

import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.Flow

class MovieRepo(private val MovieDAO: MovieDAO) {
    suspend fun addMovie(movie: Movie) = MovieDAO.insert(movie)
    suspend fun deleteMovie(movie: Movie) = MovieDAO.delete(movie)

    suspend fun deleteAllMovie() = MovieDAO.deleteAll()
    suspend fun deleteAllImages() = MovieDAO.deleteAllImages()
    suspend fun updateMovie(movie: Movie) = MovieDAO.update(movie)
    fun insertAll(movies: List<Movie>) = MovieDAO.insertMultipleMovies(movies = movies)
    suspend fun insertImage(images: MovieImages) = MovieDAO.insertImage(image = images)
    suspend fun insertAllImages(images: List<MovieImages>) = MovieDAO.insertMultipleImages(images = images)

    fun getAllMovies(): Flow<List<MovieWithImages>> = MovieDAO.getAllMovies()
    fun getAllImages(id: String?): Flow<List<MovieImages>> = MovieDAO.getAllImages(id)
    fun getFavoriteMovies(): Flow<List<MovieWithImages>> = MovieDAO.getLiked()
    fun getById(id: String?): Flow<MovieWithImages?> = MovieDAO.getSpecivicMovieById(id)

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