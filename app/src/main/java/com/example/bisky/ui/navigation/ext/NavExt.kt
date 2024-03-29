package com.example.bisky.ui.navigation.ext

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.bisky.ui.navigation.model.BottomBar
import com.example.bisky.ui.navigation.model.BottomDestination

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("ComposableNaming")
@Composable
fun rememberBottomBar(): BottomBar {
    val navData = buildMap {
        BottomDestination.values().forEach {
            put(it, rememberNavController())
        }
    }

    val pagerState = rememberPagerState { navData.size }

    return remember {
        BottomBar(
            pagerState = pagerState,
            navData = navData,
        )
    }
}
