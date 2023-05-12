package com.example.dadjokes.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JokeModel(
    val setup: String,
    val punchline: String,
    val type: String
) : Parcelable
