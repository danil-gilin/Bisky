package com.example.bisky.data.genre.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(
    @PrimaryKey
    val id: String,
    val name: String
)
