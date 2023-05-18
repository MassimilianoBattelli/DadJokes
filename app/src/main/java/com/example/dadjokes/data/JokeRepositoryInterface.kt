package com.example.dadjokes.data

import androidx.annotation.WorkerThread
import com.example.dadjokes.models.JokeModel
import com.example.dadjokes.remote.models.Joke
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface JokeRepositoryInterface {

    suspend fun fetchFavJokesFlow(): Flow<List<Joke>>
    suspend fun fetchJokesFlow(): Flow<List<Joke>>
    suspend fun searchFavourite(jokeId: String): Boolean
    suspend fun insertFavourite(joke: Joke)
    suspend fun deleteFavourite(joke: Joke)
    suspend fun  searchJokeByKeyword(keyword: String): String
}