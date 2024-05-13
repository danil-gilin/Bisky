package com.example.bisky.data.archive.remote

import com.apollographql.apollo3.ApolloClient
import com.example.GetUserCollectionAnimeQuery
import com.example.GetUserCollectionQuickSelectAnimeQuery
import com.example.bisky.common.ext.toOptional
import com.example.bisky.data.archive.mapper.mapToStatusEnum
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.anime.model.CollectionAnime
import com.example.bisky.domain.repository.archive.remote.CollectionRemoteSource
import com.example.type.GeneralAnimeQuery
import com.example.type.GeneralUserQuery
import com.example.type.UserFilterQuery
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CollectionRemoteSourceImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val dispatchersProvider: DispatchersProvider
) : CollectionRemoteSource {

    override suspend fun getUserCollectionAnime(collection: CollectionAnime) =
        withContext(dispatchersProvider.io) {
            val filterAnime = GeneralAnimeQuery(
                count = 20.toOptional(),
                userFilters = UserFilterQuery(
                    isHiddenAnimeInSkipList = false.toOptional(),
                    isHiddenAnimeInUserList = false.toOptional(),
                ).toOptional()
            )
            val filterUser = GeneralUserQuery(
                animeListStatus = collection.mapToStatusEnum().toOptional()
            )
            apolloClient.query(GetUserCollectionAnimeQuery(filterUser, filterAnime))
                .execute()
                .data
                ?.getUserPublicData
                ?.animeEstimates ?: emptyList()
        }

    override suspend fun getUserCollectionAnimePagging(
        collection: CollectionAnime,
        page: Int,
        searchInput: String
    ) =
        withContext(dispatchersProvider.io) {
            val filterAnime = GeneralAnimeQuery(
                count = 20.toOptional(),
                userFilters = UserFilterQuery(
                    isHiddenAnimeInSkipList = false.toOptional(),
                    isHiddenAnimeInUserList = false.toOptional(),
                ).toOptional(),
                page = page.toOptional(),
                searchInput = searchInput.toOptional()
            )
            val filterUser = GeneralUserQuery(
                animeListStatus = collection.mapToStatusEnum().toOptional()
            )
            apolloClient.query(GetUserCollectionAnimeQuery(filterUser, filterAnime))
                .execute()
                .data
                ?.getUserPublicData
                ?.animeEstimates ?: emptyList()
        }

    override suspend fun getUserCollectionQuickSelectAnime(
        collection: CollectionAnime,
        count: Int
    ) =
        withContext(dispatchersProvider.io) {
            val filterAnime = GeneralAnimeQuery(
                count = count.toOptional(),
                userFilters = UserFilterQuery(
                    isHiddenAnimeInSkipList = false.toOptional(),
                    isHiddenAnimeInUserList = false.toOptional(),
                ).toOptional()
            )
            val filterUser = GeneralUserQuery(
                animeListStatus = collection.mapToStatusEnum().toOptional()
            )
            apolloClient.query(GetUserCollectionQuickSelectAnimeQuery(filterUser, filterAnime))
                .execute()
                .data
                ?.getUserPublicData
                ?.animeEstimates ?: emptyList()
        }
}