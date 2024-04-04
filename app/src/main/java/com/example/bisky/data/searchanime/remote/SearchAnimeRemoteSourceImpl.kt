package com.example.bisky.data.searchanime.remote

import com.apollographql.apollo3.ApolloClient
import com.example.GetSearchAnimeQuery
import com.example.bisky.common.ext.toOptional
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.searchanime.remote.SearchAnimeRemoteSource
import com.example.type.GeneralAnimeQuery
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchAnimeRemoteSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val dispatchersProvider: DispatchersProvider
) : SearchAnimeRemoteSource {
    override suspend fun getAnimes(input: String) = withContext(dispatchersProvider.io) {
        apolloClient.query(
            GetSearchAnimeQuery(
                GeneralAnimeQuery(
                    count = 30.toOptional(),
                    searchInput = input.toOptional()
                )
            )
        )
            .execute()
            .data
            ?.getAnimes ?: emptyList()
    }
}