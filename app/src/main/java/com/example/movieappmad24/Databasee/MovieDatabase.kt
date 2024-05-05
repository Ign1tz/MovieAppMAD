package com.example.movieappmad24.Databasee

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.movieappmad24.Databasee.workers.DatabaseWorkManager
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies

@Database(
    entities = [Movie::class, MovieImages::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback = seed(context = context))
                    .build()
                    .also { instance = it }
            }
        }

        private fun seed(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val WorkManager = DatabaseWorkManager(context)
                    WorkManager.seedRequest()
                }
            }
        }
    }
}