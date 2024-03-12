package com.example.bisky.data.login.remote

import com.example.bisky.data.login.remote.model.SigInParams
import com.example.bisky.data.login.remote.model.SigInResponse

class LoginRemoteSourceImpl(
    private val loginApi: LoginApi
) {

    suspend fun sigIn(name: String, password: String): SigInResponse {
        return loginApi.sigIn(
            SigInParams(
                username = name,
                password = password
            )
        )
    }
}
