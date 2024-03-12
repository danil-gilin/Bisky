package com.example.bisky.data.login.remote

import com.example.bisky.data.login.remote.model.SigInParams
import com.example.bisky.data.login.remote.model.SigInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("auth/login")
    suspend fun sigIn(@Body params: SigInParams): SigInResponse
}