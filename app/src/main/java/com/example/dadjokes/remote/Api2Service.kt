package com.example.dadjokes.remote

import com.example.dadjokes.remote.models.Search
import com.example.dadjokes.remote.models.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api2Service {

    @Headers(
        "X-RapidAPI-Key: ab8ab25da3mshfbaffff17a25ae0p15e702jsn139134c9f43d",
        "X-RapidAPI-Host: dad-jokes7.p.rapidapi.com"
    )
    @GET("dad-jokes/search")
    suspend fun doSearch(@Query("text") keyword: String): Response<List<Search>>
}

//github ab8ab25da3mshfbaffff17a25ae0p15e702jsn139134c9f43d
//gmail 0b85b8b6aemshd09cb7b9df8eb44p120f1ejsn8522391343c4
//SD  b5f2402d5fmsh6e469fee3b71459p1a4003jsnac6cd0ab8d8e