package com.example.bisky.data.seasonanime.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "season_anime")
data class SeasonAnimeEntity(
    @PrimaryKey
    val _id: String,
    val poster: String?,
    val labels: String?,
    val genres: List<GenreEntity>?,
    val description: String?,
    val screenshots: List<String>,
    val scores: Double
)

@Entity(tableName = "genre")
@JsonClass(generateAdapter = true)
data class GenreEntity(
    @Embedded
    val name: NameEntity
)

@Entity(tableName = "genre_name")
@JsonClass(generateAdapter = true)
data class NameEntity(
    val en: String,
    val ru: String
)
