package com.example.dadjokes.data

import androidx.annotation.WorkerThread
import com.example.dadjokes.local.JokeRoomDatabase
import com.example.dadjokes.local.entities.JokeEntity
import com.example.dadjokes.remote.JokeRemoteService
import com.example.dadjokes.remote.RemoteApi.jokeRemoteService
import com.example.dadjokes.remote.models.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class JokeRepository(private val database: JokeRoomDatabase) : JokeRepositoryInterface {
    /*
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(jokeEntity: JokeEntity) {
        JokeDao.insert(jokeEntity)
    }

    fun getJokes(): Flow<List<JokeEntity>> {
        return JokeDao.getJokes()
    }
    */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun fetchJokesFlow(): Flow<List<Joke>> = flow {
        val cachedJokes: List<JokeEntity> = withContext(Dispatchers.IO) {
            database.JokeDao().getJokes()
        }

        if (cachedJokes.isNotEmpty()) {
            emit(cachedJokes.map { it.toJoke() })
            return@flow
        }

        var i = 0
        while (i < 5) {
            val metadata = jokeRemoteService.getMetadata()
            val apiJokes = metadata.body

            val jokeEntities = apiJokes.map { it.toJokeEntity() }
            withContext(Dispatchers.IO) {
                database.JokeDao().insertJokes(jokeEntities)
            }

            emit(apiJokes)

            delay(5000)
            i += 1
        }
    }
    private fun JokeEntity.toJoke(): Joke {
        return Joke( setup = this.setup,
            punchline = this.punchline,
            type = this.type,
            _id = this.id)
    }
    private fun Joke.toJokeEntity(): JokeEntity {
        return JokeEntity( setup = this.setup,
            punchline = this.punchline,
            type = this.type,
            id = this._id)
    }
}
