package com.example.dadjokes.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RemoteApi {

    private const val BASE_URL_API1 = "https://dad-jokes.p.rapidapi.com/"
    private const val BASE_URL_API2 = "https://dad-jokes7.p.rapidapi.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofitApi1 = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL_API1)
        .build()

    private val retrofitApi2 = Retrofit.Builder()
        .baseUrl(BASE_URL_API2)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val api1Service: Api1Service by lazy {
        retrofitApi1.create(Api1Service::class.java)
    }

    val api2Service: Api2Service by lazy {
        retrofitApi2.create(Api2Service::class.java)
    }
}