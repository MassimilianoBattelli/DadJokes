package com.example.dadjokes.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_joke_table")
data class FavJokeEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "setup") val setup: String,
    @ColumnInfo(name = "punchline") val punchline: String
)