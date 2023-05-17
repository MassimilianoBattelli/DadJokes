package com.example.dadjokes.data

import androidx.annotation.WorkerThread
import com.example.dadjokes.local.JokeRoomDatabase
import com.example.dadjokes.local.entities.FavJokeEntity
import com.example.dadjokes.local.entities.JokeEntity
import com.example.dadjokes.models.JokeModel
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

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun searchFavourite(jokeId: String): Boolean{
        return  database.JokeDao().getJokeById(jokeId)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertFavourite(joke: Joke){
        val favJokeEntity = joke.toFavJokeEntity()
        database.JokeDao().insertFavourite(favJokeEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun deleteFavourite(joke: Joke){
        val favJokeEntity = joke.toFavJokeEntity()
        database.JokeDao().deleteFavourite(favJokeEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun fetchFavJokesFlow(): Flow<List<Joke>> = flow {
        val favouritesJokes: List<FavJokeEntity> = withContext(Dispatchers.IO) {
            database.JokeDao().getFavJokes()
        }
        emit(favouritesJokes.map { it.toJoke() })

    }

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
            _id = this.id)
    }

    private fun Joke.toJokeEntity(): JokeEntity {
        return JokeEntity( setup = this.setup,
            punchline = this.punchline,
            id = this._id)
    }

    private fun FavJokeEntity.toJoke(): Joke {
        return Joke(setup = this.setup,
            punchline = this.punchline,
            _id = this.id)
    }

    private fun Joke.toFavJokeEntity(): FavJokeEntity {
        return FavJokeEntity( setup = this.setup,
            punchline = this.punchline,
            id = this._id)
    }



    private suspend fun <T> doesItemExist(daoQuery: suspend () -> T?): Boolean {
        return withContext(Dispatchers.IO) {
            val result = daoQuery.invoke()
            result != null
        }
    }
}
