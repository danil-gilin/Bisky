package com.example.bisky.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bisky.ui.screen.homescreen.containehomescreen.HomeContainerScreen
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
                startDestination = "boardingLogin"
            ) {
                composable("splash") {
                    SplashScreen(navController)
                }
                composable("home") {
                    HomeContainerScreen()
                }
                composable("sigIn") {
                    SigInScreen(navController = navController)
                }
                composable("sigUp") {
                    SigUpScreen(navController = navController)
                }
                composable("boardingLogin") {
                    BoardingScreen(navController = navController)
                }
            }
        }
    }
}
