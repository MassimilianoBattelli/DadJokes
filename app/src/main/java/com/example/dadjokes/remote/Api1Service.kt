package com.example.dadjokes.remote

import com.example.dadjokes.remote.models.CountResponse
import com.example.dadjokes.remote.models.JokeResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Headers

interface Api1Service {

    @Headers(
        "X-RapidAPI-Key: 0b85b8b6aemshd09cb7b9df8eb44p120f1ejsn8522391343c4",
        "X-RapidAPI-Host: dad-jokes.p.rapidapi.com"
    )
    @GET("random/joke")
    suspend fun getMetadata(): Response<JokeResponse>

    @GET("joke/count")
    suspend fun getCount(): CountResponse
}