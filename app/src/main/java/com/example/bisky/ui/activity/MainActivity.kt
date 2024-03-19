package com.example.bisky.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bisky.ui.navigation.NavigationRoute.*
import com.example.bisky.ui.screen.homescreen.containehomescreen.HomeContainerScreen
import com.example.bisky.ui.screen.homescreen.genre.genresscreen.GenreScreen
import com.example.bisky.ui.screen.loginScreen.boardingScreen.BoardingScreen
import com.example.bisky.ui.screen.loginScreen.siginscreen.SigInScreen
import com.example.bisky.ui.screen.loginScreen.sigupscreen.SigUpScreen
import com.example.bisky.ui.screen.splashScreen.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController,
                startDestination = Splash.route,
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                }
            ) {
                composable(Splash.route) {
                    SplashScreen(navController = navController)
                }
                composable(Home.route) {
                    HomeContainerScreen(navController = navController)
                }
                composable(SigIn.route) {
                    SigInScreen(navController = navController)
                }
                composable(SigUp.route) {
                    SigUpScreen(navController = navController)
                }
                composable(BoardingLogin.route) {
                    BoardingScreen(navController = navController)
                }
                composable(
                    route = "${Genre.route}/{id}/{name}",
                    arguments = listOf(
                        navArgument("id") { type = NavType.StringType },
                        navArgument("name") { type = NavType.StringType },)
                ) {
                    GenreScreen(navController = navController)
                }
            }
        }
    }
}
