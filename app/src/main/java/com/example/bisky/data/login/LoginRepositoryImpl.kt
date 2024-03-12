package com.example.bisky.data.login

import com.example.bisky.data.login.remote.LoginRemoteSourceImpl
import com.example.bisky.data.network.resultwrapper.ResultWrapper

class LoginRepositoryImpl(
    private val loginRemoteSource: LoginRemoteSourceImpl,
    private val resultWrapper: ResultWrapper
) {

    suspend fun sigIn(name: String, password: String) = resultWrapper.wrap {
        loginRemoteSource.sigIn(name, password)
    }
}
