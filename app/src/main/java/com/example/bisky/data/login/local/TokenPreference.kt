package com.example.bisky.data.login.local

import android.content.SharedPreferences
import javax.inject.Inject

class TokenPreference @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        private const val ACCESS_TOKEN_KEY = "accessToken"
        private const val REFRESH_TOKEN_KEY = "refreshToken"
    }

    @Synchronized
    fun saveToken(accessToken: String, refreshToken: String) {
        sharedPreferences
            .edit()
            .putString(ACCESS_TOKEN_KEY, accessToken)
            .putString(REFRESH_TOKEN_KEY, refreshToken)
            .apply()
    }

    @Synchronized
    fun getAccessToken() =
        sharedPreferences.getString(ACCESS_TOKEN_KEY, null)

    @Synchronized
    fun getRefreshToken() =
        sharedPreferences.getString(REFRESH_TOKEN_KEY, null)

    @Synchronized
    fun clearAccessToken() = sharedPreferences
        .edit()
        .putString(ACCESS_TOKEN_KEY, null)
        .apply()

    @Synchronized
    fun clearTokens() = sharedPreferences
        .edit()
        .putString(ACCESS_TOKEN_KEY, null)
        .putString(REFRESH_TOKEN_KEY, null)
        .apply()
}
