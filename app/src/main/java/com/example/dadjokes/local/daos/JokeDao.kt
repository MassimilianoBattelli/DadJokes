package com.example.dadjokes.local.daos

import androidx.room.*
import com.example.dadjokes.local.entities.FavJokeEntity
import com.example.dadjokes.local.entities.JokeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {

    @Query("SELECT * FROM joke_table ORDER BY id ASC")
    fun getJokes(): List<JokeEntity>

    @Query("SELECT * FROM fav_joke_table ORDER BY id ASC")
    fun getFavJokes(): List<FavJokeEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM fav_joke_table WHERE id = :jokeId)")
    suspend fun getJokeById(jokeId: String): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavourite(favJokeEntity: FavJokeEntity)

    @Delete
    suspend fun deleteFavourite(favJokeEntity: FavJokeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(jokeEntity: JokeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJokes(jokeEntities: List<JokeEntity>)

    @Query("DELETE FROM joke_table")
    suspend fun deleteAll()

}