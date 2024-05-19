package com.example.bisky.data.login.manager

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object TokenManager {

    private const val INTERVAL: Long = 9 * 60 * 1000 // 5 минут в миллисекундах
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    fun startTokenRefresh(context: Context) {
        runnable = Runnable {
            scheduleTokenRefresh(context)
            handler.postDelayed(runnable, INTERVAL)
        }
        handler.post(runnable)
    }

    fun stopTokenRefresh() {
        handler.removeCallbacks(runnable)
    }

    private fun scheduleTokenRefresh(context: Context) {
        val tokenRefreshWorkRequest = OneTimeWorkRequestBuilder<TokenRefreshWorker>()
            .build()

        WorkManager.getInstance(context).enqueue(tokenRefreshWorkRequest)
    }
}