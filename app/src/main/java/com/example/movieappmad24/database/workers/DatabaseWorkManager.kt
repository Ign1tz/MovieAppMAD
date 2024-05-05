package com.example.movieappmad24.database.workers

import android.content.Context
import androidx.work.*

class DatabaseWorkManager(context: Context) {
    private val workManager = WorkManager.getInstance(context)

    fun seedRequest() {
        val workRequestBuilder = OneTimeWorkRequestBuilder<Worker>()

        workManager.enqueue(workRequestBuilder.build())
    }
}