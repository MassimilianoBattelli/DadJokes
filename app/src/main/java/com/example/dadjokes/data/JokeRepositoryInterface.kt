package com.example.dadjokes.data

import com.example.dadjokes.remote.models.Joke
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface JokeRepositoryInterface {

    suspend fun fetchFavJokesFlow(): Flow<List<Joke>>
    suspend fun fetchJokesFlow(): Flow<List<Joke>>
}