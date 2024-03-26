package com.example.bisky.data.anime.remote

import com.example.bisky.data.anime.remote.model.RatingUpdateRequest
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Path

interface AnimeApi {

    @PATCH("user/{animeId}/score")
    suspend fun updateAnimeRating(@Body rating: RatingUpdateRequest, @Path("animeId") animeId: String) : Boolean
}