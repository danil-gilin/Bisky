package com.example.bisky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bisky.ui.screen.homescreen.containehomescreen.HomeContainerScreen
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.SeasonAnimeScreen
import com.example.bisky.ui.theme.BiskyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BiskyTheme {
                HomeContainerScreen()
            }
        }
    }
}
