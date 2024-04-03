package com.example.bisky.data.genre.mapper

import com.example.GenreAnimeQuery
import com.example.GetAllGenreWithSimpleInfoQuery
import com.example.GetGenresQuery
import com.example.bisky.common.ext.toOneNumberAfterDot
import com.example.bisky.data.genre.local.model.GenreEntity
import com.example.bisky.domain.repository.genre.model.AnimeGenre
import com.example.bisky.domain.repository.genre.model.Genre
import com.example.bisky.domain.repository.genre.model.GenreSimple
import com.example.type.StatusEnum

fun GetGenresQuery.GetGenre.mapToDomain() = Genre(
    _id = this._id,
    description = this.description.ru ?: this.description.en,
    name = this.name.ru ?: this.name.en,
    posters = this.relatedWorks.mapNotNull { it.poster }
)

fun GenreAnimeQuery.GetAnime.mapToDomain() = AnimeGenre(
    _id = this._id,
    label = this.labels.ru ?: this.labels.en,
    poster = this.poster,
    scores = this.score.averageScore.toOneNumberAfterDot(),
    status = if (this.status == StatusEnum.UNKNOWN__) null else this.status.name
)
fun GetAllGenreWithSimpleInfoQuery.GetGenre.mapToEntity() = GenreEntity(
    id = _id,
    name = this.name.ru ?: this.name.en.orEmpty()
)

fun List<GenreEntity>.mapToDomain() = this.mapNotNull {
    if (it.name.isEmpty()) return@mapNotNull null
    GenreSimple(
        id = it.id,
        name = it.name
    )
}
