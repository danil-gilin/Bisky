package com.example.bisky.data.genre.mapper

import com.example.GetGenresQuery
import com.example.bisky.domain.repository.genre.model.Genre

fun GetGenresQuery.GetGenre.mapToDomain() = Genre(
    _id = this._id,
    description = this.description.ru ?: this.description.en,
    name = this.name.ru ?: this.name.en,
    posters = this.relatedWorks.mapNotNull { it.poster }
)