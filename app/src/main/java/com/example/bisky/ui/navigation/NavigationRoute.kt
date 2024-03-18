package com.example.bisky.ui.navigation

sealed class NavigationRoute(val route: String) {
   object Splash : NavigationRoute("splash")
   object Home : NavigationRoute("home")
   object SigIn : NavigationRoute("sigIn")
   object SigUp : NavigationRoute("sigUp")
   object BoardingLogin: NavigationRoute("boardingLogin")
   object AllGenre: NavigationRoute("all_genre")
}
