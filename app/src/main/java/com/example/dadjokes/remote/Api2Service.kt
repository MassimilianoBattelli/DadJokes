package com.example.dadjokes.remote

import com.example.dadjokes.remote.models.Search
import com.example.dadjokes.remote.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api2Service {

    @Headers(
        "X-RapidAPI-Key: ab8ab25da3mshfbaffff17a25ae0p15e702jsn139134c9f43d",
        "X-RapidAPI-Host: dad-jokes7.p.rapidapi.com"
    )
    @GET("dad-jokes/search")
    suspend fun doSearch(@Query("text") keyword: String): List<Search>
}