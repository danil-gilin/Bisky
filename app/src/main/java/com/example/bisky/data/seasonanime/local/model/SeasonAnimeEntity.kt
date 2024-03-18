package com.example.bisky.data.seasonanime.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "season_anime")
data class SeasonAnimeEntity(
    @PrimaryKey
    val _id: String,
    val poster: String?,
    val labels: String?,
    val genres: List<GenreEntity>,
    val description: String?,
    val screenshots: List<String>,
    val scores: Double
)

@Entity(tableName = "genre")
data class GenreEntity(
    @Embedded
    val name: NameEntity
)

@Entity(tableName = "genre_name")
data class NameEntity(
    val en: String,
    val ru: String
)
