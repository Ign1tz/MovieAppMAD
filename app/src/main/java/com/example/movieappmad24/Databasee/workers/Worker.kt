package com.example.movieappmad24.Databasee.workers

import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.movieappmad24.Databasee.MovieDatabase.Companion.getDatabase
import com.example.movieappmad24.Databasee.MovieImages
import com.example.movieappmad24.Databasee.MovieRepo
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import kotlinx.coroutines.*

private const val TAG = "DatabaseWorker"

class Worker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    private val DAO = getDatabase(context = context).movieDao()
    private val movieRepo = MovieRepo(DAO)

    override suspend fun doWork(): Result {
        Log.d("test", "testingasdfaö")
        return coroutineScope {
            return@coroutineScope withContext(Dispatchers.Main) {
                return@withContext try {
                    getMovies().forEach { movie: Movie ->
                        movie.images.forEach {
                            movieRepo.insertImage(MovieImages(movieId = movie.id, url = it))
                        }
                        movieRepo.addMovie(movie)
                    }
                    Result.success()
                } catch (throwable: Throwable) {
                    Log.e(TAG, "An error occurred while seeding the database!", throwable)
                    Result.failure()
                }
            }
        }
    }
}