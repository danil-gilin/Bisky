package com.example.bisky.ui.navigation.model

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.bisky.ui.navigation.elements.BottomNavigation
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Stable
class BottomBar(
    val pagerState: PagerState,
    val navData: Map<BottomDestination, NavHostController>,
) {
    @SuppressLint("ComposableNaming")
    @Composable
    fun createNavComponent() {
        val coroutineScope = rememberCoroutineScope()
        BottomNavigation(
            selectedPage = pagerState.currentPage,
            onClick = {
                coroutineScope.launch {
                    switchPage(it)
                }
            },
        )
    }

    fun getNavController(key: BottomDestination) = navData[key]
        ?: navData.values.firstOrNull()
        ?: kotlin.run {
            null
        }

    private suspend fun switchPage(page: Int) {
        pagerState.scrollToPage(page)
    }
}