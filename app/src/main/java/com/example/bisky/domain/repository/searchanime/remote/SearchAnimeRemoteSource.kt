package com.example.bisky.domain.repository.searchanime.remote

import com.example.GetQuickSearchAnimeQuery
import com.example.GetSearchAnimeQuery
import com.example.bisky.domain.repository.searchanime.model.FilterSearch

interface SearchAnimeRemoteSource  {
    suspend fun getAnimes(input: String?, filter: FilterSearch, page: Int): List<GetSearchAnimeQuery.GetAnime>
    suspend fun getQuickSearchAnimes(filter: FilterSearch): List<GetQuickSearchAnimeQuery.GetAnime>
    suspend fun addToSkipList(animeId: String): Boolean
    suspend fun deleteFromSkipList(animeId: String): Boolean
}