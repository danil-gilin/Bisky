package com.example.bisky.domain.repository.newseries

import com.example.GetNewSeriesAnimeQuery

interface NewSeriesAnimeRemoteSource {
    suspend fun getNewSeriesAnime(): List<GetNewSeriesAnimeQuery.GetAnime>
}