package com.example.bisky.data.login.remote

import com.example.bisky.data.login.remote.model.SigInParams
import com.example.bisky.data.login.remote.model.SigInResponse
import com.example.bisky.data.login.remote.model.UserResponse
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {

    @POST("auth/login")
    suspend fun sigIn(@Body params: SigInParams): SigInResponse

    @GET("users/whoami")
    suspend fun checkSigIn(): UserResponse

    @GET("auth/refresh")
    suspend fun refreshToken(): SigInResponse
}