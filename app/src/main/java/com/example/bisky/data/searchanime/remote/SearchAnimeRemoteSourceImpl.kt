package com.example.bisky.data.searchanime.remote

import com.apollographql.apollo3.ApolloClient
import com.example.GetQuickSearchAnimeQuery
import com.example.GetSearchAnimeQuery
import com.example.bisky.common.ext.toOptional
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.searchanime.mapper.mapToDto
import com.example.bisky.domain.repository.searchanime.model.FilterSearch
import com.example.bisky.domain.repository.searchanime.remote.SearchAnimeRemoteSource
import com.example.type.FilterAnimeQuery
import com.example.type.FloatBetweenQuery
import com.example.type.GeneralAnimeQuery
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchAnimeRemoteSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val dispatchersProvider: DispatchersProvider
) : SearchAnimeRemoteSource {
    override suspend fun getAnimes(input: String, filter: FilterSearch) =
        withContext(dispatchersProvider.io) {
            apolloClient.query(
                GetSearchAnimeQuery(
                    GeneralAnimeQuery(
                        count = 30.toOptional(),
                        searchInput = input.toOptional(),
                        filter = FilterAnimeQuery(
                            genres_ID = filter.genres.toOptional(),
                            status = filter.status?.mapToDto().toOptional(),
                            score_averageScore = FloatBetweenQuery(
                                from = filter.scoreRange.start.toDouble().toOptional(),
                                to = filter.scoreRange.endInclusive.toDouble().toOptional()
                            ).toOptional()
                        ).toOptional()
                    )
                )
            )
                .execute()
                .data
                ?.getAnimes ?: emptyList()
        }

    override suspend fun getQuickSearchAnimes(
        filter: FilterSearch
    ) = withContext(dispatchersProvider.io) {
        apolloClient.query(
            GetQuickSearchAnimeQuery(
                GeneralAnimeQuery(
                    count = 10.toOptional(),
                    filter = FilterAnimeQuery(
                        genres_ID = filter.genres.toOptional(),
                        status = filter.status?.mapToDto().toOptional(),
                        score_averageScore = FloatBetweenQuery(
                            from = filter.scoreRange.start.toDouble().toOptional(),
                            to = filter.scoreRange.endInclusive.toDouble().toOptional()
                        ).toOptional()
                    ).toOptional()
                )
            )
        )
            .execute()
            .data
            ?.getAnimes ?: emptyList()
    }
}