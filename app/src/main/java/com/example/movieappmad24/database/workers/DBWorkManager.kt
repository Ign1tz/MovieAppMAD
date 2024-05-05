package com.example.movieappmad24.database.workers

import android.content.Context
import androidx.work.*

class DBWorkManager(context: Context) {
    private val workManager = WorkManager.getInstance(context)

    fun seedRequest() {
        workManager.enqueue(OneTimeWorkRequestBuilder<Worker>().build())
    }
}