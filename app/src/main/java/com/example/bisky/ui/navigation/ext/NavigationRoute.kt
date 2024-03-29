package com.example.bisky.ui.navigation.ext

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.bisky.ui.navigation.model.Destination
import com.example.bisky.ui.screen.animescreen.AnimeScreen
import com.example.bisky.ui.screen.homescreen.containehomescreen.HomeContainerScreen
import com.example.bisky.ui.screen.homescreen.genre.genresscreen.GenreScreen
import com.example.bisky.ui.screen.homescreen.newseriesscreen.NewSeriesScreen
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.SeasonAnimeScreen

sealed class NavigationRoute(val route: String) {
    object Splash : NavigationRoute("splash")
    object Home : NavigationRoute("home")
    object SigIn : NavigationRoute("sigIn")
    object SigUp : NavigationRoute("sigUp")
    object BoardingLogin : NavigationRoute("boardingLogin")
    object AllGenre : NavigationRoute("all_genre")
    object SeasonAnime : NavigationRoute("season_anime")
    object NewSeriesAnime : NavigationRoute("new_series_anime")
    object Genre : NavigationRoute("genre")
    object Anime : NavigationRoute("anime")
}

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(
        route = Destination.Home.route,
        startDestination = Destination.Home.SeasonAnime.route
    ) {
        composable(
            route = Destination.Home.SeasonAnime.route
        ) {
            HomeContainerScreen(navController)
        }

        composable(
            route = "${Destination.Home.Genre.route}/{id}/{name}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType },)
        ) {
            GenreScreen(navController = navController)
        }

        composable(
            route = "${Destination.Home.Anime.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            AnimeScreen(navController)
        }
    }
}

fun NavGraphBuilder.searchNavGraph(navController: NavController) {
    navigation(
        route = Destination.Search.route,
        startDestination = Destination.Search.SearchScreen.route
    ) {
        composable(route = Destination.Search.SearchScreen.route) {
            NewSeriesScreen()
        }
    }
}
