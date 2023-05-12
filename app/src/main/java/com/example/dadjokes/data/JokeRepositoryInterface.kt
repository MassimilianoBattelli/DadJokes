package com.example.dadjokes.data

import com.example.dadjokes.remote.models.JokeResponse
import com.example.dadjokes.remote.models.JokeResponseWrapper
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface JokeRepositoryInterface {

    suspend fun fetchJokesFlow(): Flow<List<JokeResponse>>
}