package com.example.dadjokes.remote.models

data class Joke(
    val _id: String,
    val setup: String,
    val punchline: String,
    val type: String,
    val likes: List<Any>,
    val author: Author,
    val approved: Boolean,
    val date: Long,
    val NSFW: Boolean,
    val shareableLink: String
)