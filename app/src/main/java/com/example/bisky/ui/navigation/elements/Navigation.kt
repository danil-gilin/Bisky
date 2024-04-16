package com.example.bisky.ui.navigation.elements

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import com.example.bisky.ui.navigation.ext.archiveNavGraph
import com.example.bisky.ui.navigation.model.Destination
import com.example.bisky.ui.navigation.ext.homeNavGraph
import com.example.bisky.ui.navigation.ext.profileNavGraph
import com.example.bisky.ui.navigation.model.BottomDestination
import com.example.bisky.ui.navigation.ext.rememberBottomBar
import com.example.bisky.ui.navigation.ext.searchNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Navigation(
    navController: NavController,
    viewModel: NavigationViewModel = hiltViewModel()
) {
    val bottomBar = rememberBottomBar()
    val uiState by viewModel.uiState.collectAsState()
    viewModel.initNavController(navController)
    Scaffold(bottomBar = {
        if (uiState.isBottomNavVisible) {
            bottomBar.createNavComponent()
        }
    }) {
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
                archiveNavGraph(navController = navController)
                profileNavGraph(navController = navController)
            }
        }
    }
}
