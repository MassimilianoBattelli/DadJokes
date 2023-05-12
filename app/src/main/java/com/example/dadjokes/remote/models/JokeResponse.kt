package com.example.dadjokes.remote.models

data class JokeResponse(
    val _id: String,
    val punchline: String,
    val setup: String,
    val type: String,
    val success: Boolean
)