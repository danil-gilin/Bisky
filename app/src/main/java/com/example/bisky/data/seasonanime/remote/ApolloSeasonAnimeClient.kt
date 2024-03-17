package com.example.bisky.data.seasonanime.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.example.SeasonAnimeQuery
import com.example.bisky.data.seasonanime.mapper.mapToDomain
import com.example.bisky.data.seasonanime.remote.model.SeasonAnime
import com.example.bisky.domain.repository.seasonanime.model.RequestSeasonAnimeParams
import com.example.bisky.utils.ext.toOptional
import com.example.type.DateBetweenQuery
import com.example.type.FilterAnimeQuery
import com.example.type.GeneralAnimeQuery
import com.example.type.SortAnimeQuery
import javax.inject.Inject

class ApolloSeasonAnimeClient @Inject constructor(
    private val apolloClient: ApolloClient
) : SeasonAnimeClient {
    override suspend fun getAnimeSeason(params: RequestSeasonAnimeParams): List<SeasonAnime> {
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
        return apolloClient.query(SeasonAnimeQuery(filter))
            .execute()
            .data
            ?.getAnimes
            ?.map { it.mapToDomain() } ?: emptyList()
    }
}
