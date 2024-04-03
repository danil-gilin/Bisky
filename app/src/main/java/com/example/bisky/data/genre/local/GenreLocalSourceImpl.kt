package com.example.bisky.data.genre.local

import com.example.bisky.data.genre.local.model.GenreEntity
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.genre.local.GenreLocalSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreLocalSourceImpl @Inject constructor(
    private val genreDao: GenreDao,
    private val dispatchersProvider: DispatchersProvider
) : GenreLocalSource {
    override suspend fun fetchGenres() = withContext(dispatchersProvider.io) {
        genreDao.getGenres()
    }

    override suspend fun insertGenres(genres: List<GenreEntity>) = withContext(dispatchersProvider.io) {
        genreDao.insertList(genres)
    }
}