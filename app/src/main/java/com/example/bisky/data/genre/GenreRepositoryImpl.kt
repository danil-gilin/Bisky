package com.example.bisky.data.genre

import com.example.bisky.data.genre.mapper.mapToDomain
import com.example.bisky.data.genre.mapper.mapToEntity
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.genre.GenreRepository
import com.example.bisky.domain.repository.genre.local.GenreLocalSource
import com.example.bisky.domain.repository.genre.remote.GenreRemoteSource
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val genreRemoteSource: GenreRemoteSource,
    private val genreLocalSource: GenreLocalSource,
    private val resultWrapper: ResultWrapper
) : GenreRepository {

    override suspend fun getGenres(page: Int) = resultWrapper.wrap {
        genreRemoteSource.getGenres(page).map { it.mapToDomain() }
    }

    override suspend fun getAnimesGenre(genreId: String, page: Int) = resultWrapper.wrap {
        genreRemoteSource.getAnimesGenre(genreId, page).map { it.mapToDomain() }
    }

    override suspend fun getAllGenresWithSimpleInfo() = resultWrapper.wrap {
        val entity = genreLocalSource.fetchGenres()
        if (entity.isEmpty()) {
            val entity = genreRemoteSource.getAllGenre().map { it.mapToEntity() }
            genreLocalSource.insertGenres(entity)
            genreLocalSource.fetchGenres().mapToDomain()
        } else {
            entity.mapToDomain()
        }
    }
}


