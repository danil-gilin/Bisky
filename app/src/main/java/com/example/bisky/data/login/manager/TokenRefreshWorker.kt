package com.example.bisky.data.login.manager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.login.LoginRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltWorker
class TokenRefreshWorker @AssistedInject constructor(
    private val loginRepository: LoginRepository,
    @Assisted appContext : Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            var result =  Result.retry()
            loginRepository.refreshToken().onSuccess {
                result = Result.success()
            }
            return@withContext result
        }
    }
}