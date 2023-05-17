package com.example.dadjokes.ui

import com.example.dadjokes.models.JokeModel

interface JokeAdapterListener {
    suspend fun onFavouriteButtonClicked(joke: JokeModel)
    suspend fun searchFavourite(joke: JokeModel): Boolean
}