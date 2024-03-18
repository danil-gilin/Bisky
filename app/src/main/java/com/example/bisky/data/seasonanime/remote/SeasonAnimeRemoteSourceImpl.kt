package com.example.bisky.data.seasonanime.remote

import com.apollographql.apollo3.ApolloClient
import com.example.SeasonAnimeQuery
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.seasonanime.model.RequestSeasonAnimeParams
import com.example.bisky.domain.repository.seasonanime.remote.SeasonAnimeRemoteSource
import com.example.bisky.utils.ext.toOptional
import com.example.type.DateBetweenQuery
import com.example.type.FilterAnimeQuery
import com.example.type.GeneralAnimeQuery
import com.example.type.SortAnimeQuery
import javax.inject.Inject
import kotlinx.coroutines.withContext

class SeasonAnimeRemoteSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val dispatchersProvider: DispatchersProvider
) : SeasonAnimeRemoteSource {
    override suspend fun getAnimeSeason(params: RequestSeasonAnimeParams) =
        withContext(dispatchersProvider.io) {
            val filter = GeneralAnimeQuery(
                count = 100.toOptional(),
                filter = FilterAnimeQuery(
                    dates_airedOn = DateBetweenQuery(
                        from = params.startDate.toOptional(),
                        to = params.endDate.toOptional()
                    ).toOptional()
                ).toOptional(),
                sort = SortAnimeQuery(
                    dates_airedOn = true.toOptional()
                ).toOptional()
            )
            apolloClient.query(SeasonAnimeQuery(filter))
                .execute()
                .data
                ?.getAnimes
        }
}
