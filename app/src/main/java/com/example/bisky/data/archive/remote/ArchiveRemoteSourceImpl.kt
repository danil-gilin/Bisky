package com.example.bisky.data.archive.remote

import com.apollographql.apollo3.ApolloClient
import com.example.GetUserCollectionAnimeQuery
import com.example.bisky.common.ext.toOptional
import com.example.bisky.data.archive.mapper.mapToStatusEnum
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.remote.CollectionRemoteSource
import com.example.type.GeneralAnimeQuery
import com.example.type.GeneralUserQuery
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArchiveRemoteSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val dispatchersProvider: DispatchersProvider
) : CollectionRemoteSource {

    override suspend fun getUserCollectionAnime(collection: CollectionAnime) =
        withContext(dispatchersProvider.io) {
            val filterAnime = GeneralAnimeQuery(
                count = 20.toOptional()
            )
            val filterUser = GeneralUserQuery(
                animeListStatus = collection.mapToStatusEnum().toOptional()
            )
            apolloClient.query(GetUserCollectionAnimeQuery(filterUser,filterAnime))
                .execute()
                .data
                ?.getUserPublicData
                ?.animeEstimates ?: emptyList()
        }

}