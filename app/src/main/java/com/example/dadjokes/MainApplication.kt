package com.example.dadjokes

import android.app.Application
import com.example.dadjokes.data.JokeRepository
import com.example.dadjokes.local.JokeRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { JokeRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { JokeRepository(database) }
}
