package com.example.bisky.domain.repository.searchanime.remote

import com.example.GetSearchAnimeQuery

interface SearchAnimeRemoteSource  {
    suspend fun getAnimes(input: String): List<GetSearchAnimeQuery.GetAnime>
}