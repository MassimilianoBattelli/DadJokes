package com.example.dadjokes.data

import androidx.annotation.WorkerThread
import com.example.dadjokes.local.daos.JokeDao
import com.example.dadjokes.local.entities.JokeEntity
import com.example.dadjokes.remote.RemoteApi
import com.example.dadjokes.remote.RemoteApi.JokeRemoteService
import com.example.dadjokes.remote.models.JokeResponse
import com.example.dadjokes.remote.models.JokeResponseWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

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
    override suspend fun fetchJokesFlow(): Flow<List<JokeResponse>> = flow {
        while (true) {
            val metadata = JokeRemoteService.getMetadata()
            val body = metadata.body

            val jokeList = listOf(body) // Create a list with a single JokeResponse
            emit(jokeList)
            delay(5000)
        }
    }

}
