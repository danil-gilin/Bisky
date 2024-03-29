package com.example.bisky.ui.screen.homescreen.containehomescreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bisky.ui.navigation.ext.NavigationRoute.AllGenre
import com.example.bisky.ui.navigation.ext.NavigationRoute.NewSeriesAnime
import com.example.bisky.ui.navigation.ext.NavigationRoute.SeasonAnime
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.AllGenreScreen
import com.example.bisky.ui.screen.homescreen.newseriesscreen.NewSeriesScreen
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.SeasonAnimeScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SeasonAnime.route
    ) {

        composable(
            route = SeasonAnime.route
        ) {
            SeasonAnimeScreen(navController = navController)
        }
        composable(
            route = AllGenre.route
        ) {
            AllGenreScreen(navController = navController)
        }
        composable(
            route = NewSeriesAnime.route
        ) {
            NewSeriesScreen()
        }
    }
}