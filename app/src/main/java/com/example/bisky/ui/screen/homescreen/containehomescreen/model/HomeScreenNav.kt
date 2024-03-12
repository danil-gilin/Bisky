package com.example.bisky.ui.screen.homescreen.containehomescreen.model

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bisky.ui.screen.homescreen.newseriesscreen.NewSeriesScreen
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.SeasonAnimeScreen

object HomeScreenNav {
    val Season = "Season"
    val Genre = "Genre"
    val New = "New"
    val MAIN_ROUT = "Home_rout"
}

fun NavGraphBuilder.addHomeGraph(
    navController: NavController
) {
    composable(HomeScreenNav.Season) {
        SeasonAnimeScreen()
    }
    composable(HomeScreenNav.Genre) {
        NewSeriesScreen()
    }
    composable(HomeScreenNav.New) {
        NewSeriesScreen()
    }
}

fun NavGraphBuilder.navGraphHome(navController: NavController) {
    navigation(
        route = HomeScreenNav.MAIN_ROUT,
        startDestination = HomeScreenNav.Season
    ) {
        addHomeGraph(navController)
    }
}
