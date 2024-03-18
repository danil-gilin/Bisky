package com.example.bisky.data.login.local

import com.example.bisky.data.login.local.model.UserEntity
import com.example.bisky.data.network.dispatcher.DispatchersProvider
import com.example.bisky.domain.repository.login.local.LoginLocalSource
import javax.inject.Inject
import kotlinx.coroutines.withContext

class LoginLocalSourceImpl @Inject constructor(
    private val loginDao: LoginDao,
    private val dispatchersProvider: DispatchersProvider
) : LoginLocalSource {
    override suspend fun fetchUser() = withContext(dispatchersProvider.io) {
        loginDao.fetchUser()
    }

    override suspend fun updateUser(userEntity: UserEntity) = withContext(dispatchersProvider.io) {
        loginDao.clearUser()
        loginDao.insert(userEntity)
    }
}
