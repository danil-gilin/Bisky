package com.example.bisky.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bisky.ui.screen.homescreen.containehomescreen.HomeContainerScreen
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
                startDestination = "splash"
            ) {
                composable("splash") {
                    SplashScreen(navController)
                }
                composable("home") {
                    HomeContainerScreen()
                }
            }
        }
    }
}
