package com.example.bisky.domain.repository.anime.local

import com.example.bisky.domain.repository.archive.local.model.AddAnime
import com.example.bisky.domain.repository.archive.local.model.CompleteAnime
import com.example.bisky.domain.repository.archive.local.model.WatchAnime

interface AnimeLocalSource {

    suspend fun addToWatchCollection(watchAnime: WatchAnime)
    suspend fun addToCompleteCollection(completeAnime: CompleteAnime)
    suspend fun addToAddCollection(addAnime: AddAnime)
    suspend fun addToWatchCollection(watchAnime: List<WatchAnime>)
    suspend fun addToCompleteCollection(completeAnime: List<CompleteAnime>)
    suspend fun addToAddCollection(addAnime: List<AddAnime>)
    suspend fun clearAnimeCollection(id: String)
}
