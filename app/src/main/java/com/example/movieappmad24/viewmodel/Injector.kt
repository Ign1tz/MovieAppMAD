package com.example.movieappmad24.dependency_injection

import android.content.Context
import com.example.movieappmad24.database.MovieDatabase
import com.example.movieappmad24.database.MovieRepo
import com.example.movieappmad24.view_models.MovieViewModelFactory

object Injector {
    private fun getMovieRepo(context: Context): MovieRepo {
        return MovieRepo.getInstance(MovieDatabase.getDatabase(context.applicationContext).movieDao())
    }

    fun provideMovieViewModelFactory(context: Context): MovieViewModelFactory {
        val Repo = getMovieRepo(context)
        return MovieViewModelFactory(movieRepo = Repo)
    }
}