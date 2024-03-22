package com.example.bisky.data.anime

import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.domain.repository.anime.AnimeRepository
import com.example.bisky.domain.repository.anime.remote.AnimeRemoteSource
import com.example.bisky.domain.repository.anime.local.AnimeLocalSource
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeClient: AnimeRemoteSource,
    private val animeLocalSource: AnimeLocalSource,
    private val resultWrapper: ResultWrapper
) : AnimeRepository {

    suspend fun getAnime(id: String) = resultWrapper.wrap {
        animeClient.getAnime(id)
    }
}
