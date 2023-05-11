package com.example.tsi_application

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "joke_table")
data class JokeEntity(@PrimaryKey @ColumnInfo(name = "joke")val joke: String)