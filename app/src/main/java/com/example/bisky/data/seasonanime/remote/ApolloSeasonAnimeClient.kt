package com.example.bisky.data.seasonanime.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.example.SeasonAnimeQuery
import com.example.bisky.data.seasonanime.mapper.mapToDomain
import com.example.bisky.data.seasonanime.remote.model.SeasonAnime
import com.example.bisky.utils.ext.toOptional
import com.example.type.AiredOnFilter
import com.example.type.FilterArgs
import javax.inject.Inject

class ApolloSeasonAnimeClient @Inject constructor(
    private val apolloClient: ApolloClient
) : SeasonAnimeClient {
    override suspend fun getAnimeSeason(): List<SeasonAnime> {
        val filter = FilterArgs(
            screenshotsCount = 5.toOptional(),
            labelCount = 1.toOptional(),
            airedOn = AiredOnFilter(
                "2023.12.01".toOptional(),
                "2024.02.28".toOptional()
            ).toOptional()
        )
        return apolloClient.query(SeasonAnimeQuery(20, Optional.present(filter)))
            .execute()
            .data
            ?.getAnimePages
            ?.map { it.mapToDomain() } ?: emptyList()
    }
}