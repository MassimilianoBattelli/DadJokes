package com.example.dadjokes.data

import androidx.annotation.WorkerThread
import com.example.dadjokes.local.JokeRoomDatabase
import com.example.dadjokes.local.entities.FavJokeEntity
import com.example.dadjokes.local.entities.JokeEntity
import com.example.dadjokes.remote.RemoteApi.api1Service
import com.example.dadjokes.remote.RemoteApi.api2Service
import com.example.dadjokes.remote.models.Joke
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext


class JokeRepository(private val database: JokeRoomDatabase) : JokeRepositoryInterface {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun searchJokeByKeyword(keyword: String): String {

        val response = api2Service.doSearch(keyword)

        if (response.isSuccessful) {
            val jokeList = response.body()
            if (jokeList != null && jokeList.isNotEmpty()) {
                return jokeList.joinToString(separator = "\n\n") { it.joke }
            } else {
                return "No joke found by keyword: '$keyword'"
            }
        } else {
            if (response.code() == 429) {
                return "Too many requests. Upgrade your plan or wait tomorrow"
            } else if (response.code() == 400) {
                return "Query parameter length should be more than 3 characters."
            }
        }
        return "An unknown error occurred"
    }

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
        val collectedJokes = mutableListOf<Joke>()
        while (i < 5) {
            val metadata = api1Service.getMetadata()
            val apiJokes = metadata.body

            val jokeEntities = apiJokes.map { it.toJokeEntity() }
            withContext(Dispatchers.IO) {
                database.JokeDao().insertJokes(jokeEntities)
            }

            collectedJokes.addAll(apiJokes)
            i += 1
        }
        emit(collectedJokes)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun refreshJokesFlow(): Flow<List<Joke>> = flow {
        database.JokeDao().deleteAll()
        var i = 0
        val collectedJokes = mutableListOf<Joke>()
        while (i < 5) {
            val metadata = api1Service.getMetadata()
            val apiJokes = metadata.body

            val jokeEntities = apiJokes.map { it.toJokeEntity() }
            withContext(Dispatchers.IO) {
                database.JokeDao().insertJokes(jokeEntities)
            }

            collectedJokes.addAll(apiJokes)
            i += 1
        }
        emit(collectedJokes)
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

}
