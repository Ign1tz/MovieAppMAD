package com.example.movieappmad24.Databasee.workers

import android.content.Context
import android.util.Log
import androidx.work.*

class DatabaseWorkManager(context: Context) {
    private val workManager = WorkManager.getInstance(context)

    fun seedRequest() {
        val workRequestBuilder = OneTimeWorkRequestBuilder<Worker>()

        workManager.enqueue(workRequestBuilder.build())
    }
}