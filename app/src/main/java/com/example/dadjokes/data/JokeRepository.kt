package com.example.dadjokes.data

import com.example.dadjokes.remote.RemoteApi.JokeRemoteService
import com.example.dadjokes.remote.models.Joke
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class JokeRepository : JokeRepositoryInterface {
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
    override suspend fun fetchJokesFlow(): Flow<List<Joke>> = flow {
        while (true) {
            val metadata = JokeRemoteService.getMetadata()
            val body = metadata.body

            emit(body)
            delay(5000)
        }
    }

}