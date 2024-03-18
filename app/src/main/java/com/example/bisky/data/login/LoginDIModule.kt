package com.example.bisky.data.login

import android.content.SharedPreferences
import com.example.bisky.data.login.local.LoginDao
import com.example.bisky.data.login.local.LoginLocalSourceImpl
import com.example.bisky.data.login.local.TokenPreference
import com.example.bisky.data.login.remote.LoginApi
import com.example.bisky.data.login.remote.LoginRemoteSourceImpl
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.data.network.resultwrapper.ResultWrapper
import com.example.bisky.data.room.AppDatabase
import com.example.bisky.domain.repository.login.LoginRepository
import com.example.bisky.domain.repository.login.local.LoginLocalSource
import com.example.bisky.domain.repository.login.remote.LoginRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object LoginDIModule {
    @Singleton
    @Provides
    fun provideLoginAPi(retrofit: Retrofit) = retrofit.create(LoginApi::class.java)

    @Singleton
    @Provides
    fun provideLoginDao(appDatabase: AppDatabase) = appDatabase.loginDao()

    @Singleton
    @Provides
    fun provideLoginRemoteSource(
        loginApi: LoginApi,
        dispatchersProvider: DispatchersProvider
    ): LoginRemoteSource =
        LoginRemoteSourceImpl(
            loginApi,
            dispatchersProvider
        )

    @Singleton
    @Provides
    fun provideTokenPreference(sharedPreferences: SharedPreferences) = TokenPreference(
        sharedPreferences
    )

    @Singleton
    @Provides
    fun provideLoginLocalSource(
        loginDao: LoginDao,
        dispatchersProvider: DispatchersProvider
    ): LoginLocalSource = LoginLocalSourceImpl(
        loginDao,
        dispatchersProvider
    )

    @Singleton
    @Provides
    fun provideLoginRepository(
        loginRemoteSource: LoginRemoteSource,
        tokenPreference: TokenPreference,
        loginLocalSource: LoginLocalSource,
        resultWrapper: ResultWrapper
    ): LoginRepository = LoginRepositoryImpl(
        loginRemoteSource = loginRemoteSource,
        tokenPreference = tokenPreference,
        loginLocalSource = loginLocalSource,
        resultWrapper = resultWrapper
    )
}
