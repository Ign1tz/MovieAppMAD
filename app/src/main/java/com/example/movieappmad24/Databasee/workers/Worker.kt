package com.example.movieappmad24.Databasee.workers

import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.movieappmad24.Databasee.MovieDatabase.Companion.getDatabase
import com.example.movieappmad24.Databasee.MovieImages
import com.example.movieappmad24.Databasee.MovieRepo
import com.example.movieappmad24.models.getMovies
import kotlinx.coroutines.*

private const val TAG = "DatabaseWorker"

class Worker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    private val DAO = getDatabase(context = context).movieDao()
    private val movieRepo = MovieRepo(DAO)

    override suspend fun doWork(): Result {
        return coroutineScope {
            return@coroutineScope withContext(Dispatchers.Main) {
                return@withContext try {
                    movieRepo.insertAll(getMovies())
                    val movieImages = mutableListOf<MovieImages>()
                    for (movie in getMovies()) {
                        for (url in movie.images) {
                            movieImages.add(
                                element = MovieImages(
                                    movieId = movie.id,
                                    url = url
                                )
                            )
                        }
                    }
                    //movieRepo.insertAllImages(movieImages)
                    Result.success()
                } catch (throwable: Throwable) {
                    Log.e(TAG, "An error occurred while seeding the database!", throwable)
                    Result.failure()
                }
            }
        }
    }
}