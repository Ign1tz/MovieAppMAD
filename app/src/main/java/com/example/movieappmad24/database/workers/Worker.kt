package com.example.movieappmad24.database.workers

import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.movieappmad24.database.MovieDatabase.Companion.getDatabase
import com.example.movieappmad24.database.*
import kotlinx.coroutines.*

private const val TAG = "DatabaseWorker"

class Worker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    private val dao = getDatabase(context = context).movieDao()
    private val movieRepo = MovieRepo(dao)

    override suspend fun doWork(): Result {
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