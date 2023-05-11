package com.example.tsi_application

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.tsi_application.JokeEntity

@Dao
interface JokeDao {

    @Query("SELECT * FROM joke_table ORDER BY joke ASC")
    fun getAlphabetizedWords(): Flow<List<JokeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(jokeEntity: JokeEntity)

    @Query("DELETE FROM joke_table")
    suspend fun deleteAll()

}