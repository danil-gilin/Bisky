package com.example.bisky.data.anime.remote

import com.example.bisky.data.anime.remote.model.RatingUpdateRequest
import com.example.bisky.data.anime.remote.model.StatusUpdateRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.Path

interface AnimeApi {

    @PATCH("user/{animeId}/score")
    suspend fun updateAnimeRating(@Body rating: RatingUpdateRequest, @Path("animeId") animeId: String) : Boolean

    @PATCH("user/{animeId}/status")
    suspend fun updateAnimeStatus(@Body rating: StatusUpdateRequest, @Path("animeId") animeId: String) : Boolean

    @DELETE("user/{animeId}/status")
    suspend fun deleteAnimeStatus(@Path("animeId") animeId: String) : Boolean
}