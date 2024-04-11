package com.example.bisky.domain.repository.searchanime.remote

import com.example.GetQuickSearchAnimeQuery
import com.example.GetSearchAnimeQuery
import com.example.bisky.domain.repository.searchanime.model.FilterSearch
import java.util.concurrent.atomic.AtomicReference

interface SearchAnimeRemoteSource  {
    suspend fun getAnimes(input: String?, filter: FilterSearch): List<GetSearchAnimeQuery.GetAnime>
    suspend fun getQuickSearchAnimes(filter: FilterSearch): List<GetQuickSearchAnimeQuery.GetAnime>
}