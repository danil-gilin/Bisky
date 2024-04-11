package com.example.bisky.domain.repository.archive

import com.example.bisky.data.network.resultwrapper.Result
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.model.AnimeUserCollection

interface ArchiveRepository {
    suspend fun getUserCollectionAnime(collection: CollectionAnime): Result<List<AnimeUserCollection>, Throwable>
}