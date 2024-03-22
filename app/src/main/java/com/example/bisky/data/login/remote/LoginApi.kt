package com.example.bisky.data.login.remote

import com.example.bisky.data.login.remote.model.TokenResponse
import com.example.bisky.data.login.remote.model.UserResponse
import com.example.bisky.domain.repository.login.model.SigInParams
import com.example.bisky.domain.repository.login.model.SigUpParams
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {

    @POST("auth/login")
    suspend fun sigIn(@Body params: SigInParams): TokenResponse

    @POST("auth/register")
    suspend fun sigUp(@Body params: SigUpParams): TokenResponse

    @GET("auth/whoami")
    suspend fun checkSigIn(): UserResponse

    @GET("auth/refresh")
    suspend fun refreshToken(): TokenResponse
}
