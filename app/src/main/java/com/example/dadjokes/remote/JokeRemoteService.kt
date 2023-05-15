package com.example.dadjokes.remote

import com.example.dadjokes.remote.models.CountResponse
import com.example.dadjokes.remote.models.JokeResponse

import retrofit2.http.GET
import retrofit2.http.Headers

interface JokeRemoteService {

    @Headers(
        "X-RapidAPI-Key: ab8ab25da3mshfbaffff17a25ae0p15e702jsn139134c9f43d",
        "X-RapidAPI-Host: dad-jokes.p.rapidapi.com"
    )
    @GET("random/joke")
    suspend fun getMetadata(): JokeResponse

    @GET("joke/count")
    suspend fun getCount(): CountResponse
}