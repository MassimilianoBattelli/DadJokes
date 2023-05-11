package com.example.tsi_application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [JokeEntity::class], version = 1, exportSchema = false)
public abstract class JokeRoomDatabase : RoomDatabase() {

    abstract fun JokeDao(): JokeDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: JokeRoomDatabase? = null

        fun getDatabase(context: Context,  scope: CoroutineScope): JokeRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JokeRoomDatabase::class.java,
                    "Joke_database"
                )
                .addCallback(JokeDatabaseCallback(scope))
                .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
    private class JokeDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.JokeDao())
                }
            }
        }

        suspend fun populateDatabase(jokeDao: JokeDao) {
            // Delete all content here.
            jokeDao.deleteAll()

            // Add sample words.
            var joke = JokeEntity("Hello")
            jokeDao.insert(joke)
            joke = JokeEntity("World!")
            jokeDao.insert(joke)

            // TODO: Add your own words!
        }
    }
}