package com.example.dadjokes.remote.models

data class JokeResponse(
    val success: Boolean,
    val body: List<Joke>
)