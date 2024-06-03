package com.example.bisky.data.searchanime.remote

import com.example.bisky.data.searchanime.remote.model.SkipListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH

interface SearchApi {

    @PATCH("user/skipList")
    suspend fun addToSkipList(@Body skipListResponse: SkipListResponse) : Boolean

    @DELETE("user/skipList")
    suspend fun deleteFromSkipList(@Body skipListResponse: SkipListResponse) : Boolean
}