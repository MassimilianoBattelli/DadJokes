package com.example.dadjokes.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dadjokes.local.entities.JokeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {

    @Query("SELECT * FROM joke_table ORDER BY id ASC")
    fun getJokes(): List<JokeEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(jokeEntity: JokeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJokes(jokeEntities: List<JokeEntity>)

    @Query("DELETE FROM joke_table")
    suspend fun deleteAll()

}