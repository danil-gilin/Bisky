package com.example.bisky.ui.navigation.ext

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.bisky.ui.navigation.model.Destination
import com.example.bisky.ui.screen.animescreen.AnimeScreen
import com.example.bisky.ui.screen.archivepage.container.ArchiveContainerScreen
import com.example.bisky.ui.screen.archivepage.quickselect.QuickSelectScreen
import com.example.bisky.ui.screen.homescreen.containehomescreen.HomeContainerScreen
import com.example.bisky.ui.screen.homescreen.genre.genresscreen.GenreScreen
import com.example.bisky.ui.screen.searchpage.filterscreen.FilterScreen
import com.example.bisky.ui.screen.searchpage.quicksearch.QuickSearchScreen
import com.example.bisky.ui.screen.searchpage.searchrootscreen.SearchScreen
import com.example.bisky.ui.screen.userspage.userhomesscreen.UserHomeScreen

sealed class NavigationRoute(val route: String) {
    object Splash : NavigationRoute("splash")
    object Home : NavigationRoute("home")
    object SigIn : NavigationRoute("sigIn")
    object SigUp : NavigationRoute("sigUp")
    object BoardingLogin : NavigationRoute("boardingLogin")
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
            SearchScreen(navController)
        }
        composable(
            route = "${Destination.Search.Anime.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            AnimeScreen(navController)
        }
        composable(route = Destination.Search.Filter.route) {
            FilterScreen(navController)
        }
        composable(route = Destination.Search.QuickSearch.route) {
            QuickSearchScreen(navController = navController)
        }
    }
}

fun NavGraphBuilder.archiveNavGraph(navController: NavController) {
    navigation(
        route = Destination.Archive.route,
        startDestination = Destination.Archive.ContainerScreen.route
    ) {
        composable(route = Destination.Archive.ContainerScreen.route) {
            ArchiveContainerScreen(navController)
        }
        composable(route = Destination.Archive.QuickSelectScreen.route) {
            QuickSelectScreen(navController)
        }
        composable(
            route = "${Destination.Archive.Anime.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            AnimeScreen(navController)
        }
    }
}

fun NavGraphBuilder.profileNavGraph(navController: NavController) {
    navigation(
        route = Destination.Profile.route,
        startDestination = Destination.Profile.HomeScreen.route
    ) {
        composable(route = Destination.Profile.HomeScreen.route) {
            UserHomeScreen(navController)
        }
        composable(
            route = "${Destination.Profile.Anime.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            AnimeScreen(navController)
        }
    }
}
