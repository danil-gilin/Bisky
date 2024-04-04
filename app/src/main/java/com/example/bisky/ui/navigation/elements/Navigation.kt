package com.example.bisky.ui.navigation.elements

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.example.bisky.ui.navigation.model.Destination
import com.example.bisky.ui.navigation.ext.homeNavGraph
import com.example.bisky.ui.navigation.model.BottomDestination
import com.example.bisky.ui.navigation.ext.rememberBottomBar
import com.example.bisky.ui.navigation.ext.searchNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Navigation() {
    val bottomBar = rememberBottomBar()

    Scaffold(bottomBar = { bottomBar.createNavComponent() }) {
        HorizontalPager(
            state = bottomBar.pagerState,
            userScrollEnabled = false
        ) { page ->
            val bottomDestination = BottomDestination.getBy(page)
            val navController =
                bottomBar.getNavController(bottomDestination) ?: return@HorizontalPager
            NavHost(
                navController = navController,
                startDestination = Destination.getStartDestinationBy(page),
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                }
            ) {

                homeNavGraph(navController = navController)
                searchNavGraph(navController = navController)
            }
        }
    }
}
