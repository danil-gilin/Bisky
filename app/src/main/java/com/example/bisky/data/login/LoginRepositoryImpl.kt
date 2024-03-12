package com.example.bisky.data.login

import com.example.bisky.data.login.local.TokenPreference
import com.example.bisky.data.login.remote.LoginRemoteSourceImpl
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import retrofit2.HttpException

class LoginRepositoryImpl(
    private val loginRemoteSource: LoginRemoteSourceImpl,
    private val tokenPreference: TokenPreference,
    private val resultWrapper: ResultWrapper
) {

    suspend fun sigIn(name: String, password: String) = resultWrapper.wrap {
        val result = loginRemoteSource.sigIn(name, password)
        tokenPreference.saveToken(result.accessToken, result.refreshToken)
    }

    suspend fun checkSigIn() = resultWrapper.wrap {
        try {
            loginRemoteSource.checkSigIn()
        } catch (e: HttpException){
            if (e.code() == 401) {
                tokenPreference.clearAccessToken()
                val result = loginRemoteSource.refreshToken()
                tokenPreference.saveToken(result.accessToken, result.refreshToken)
            } else {
                throw e
            }
        }
    }
}
