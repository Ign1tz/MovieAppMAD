package com.example.movieappmad24.Databasee

import kotlinx.coroutines.flow.Flow

class MovieRepo(private val MovieDAO: MovieDAO) {
    suspend fun addMovie(movie: Movie) = MovieDAO.insert(movie)
    suspend fun deleteMovie(movie: Movie) = MovieDAO.delete(movie)
    suspend fun updateMovie(movie: Movie) = MovieDAO.update(movie)

    fun getAllMovies(): Flow<List<Movie>> = MovieDAO.getAll()
    fun getFavoriteMovies(): Flow<List<Movie>> = MovieDAO.getLiked()
    fun getById(id: String): Flow<Movie?> = MovieDAO.getSpecivicMovie(id)
}