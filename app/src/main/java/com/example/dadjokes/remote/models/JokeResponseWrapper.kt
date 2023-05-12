package com.example.dadjokes.remote.models

import okhttp3.Headers

data class JokeResponseWrapper(
    val code: Int,
    val headers: Headers,
    val body: JokeResponse
)