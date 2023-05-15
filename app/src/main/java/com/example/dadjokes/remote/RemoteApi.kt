package com.example.dadjokes.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RemoteApi {

    private const val BASE_URL = "https://dad-jokes.p.rapidapi.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val jokeRemoteService: JokeRemoteService by lazy {
        retrofit.create(JokeRemoteService::class.java)
    }
}