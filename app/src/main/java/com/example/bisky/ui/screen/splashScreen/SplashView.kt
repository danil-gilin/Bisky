package com.example.bisky.ui.screen.splashScreen

import androidx.navigation.NavHostController

interface SplashView {

    sealed class Action {
        data class CheckSigIn(val navHostController: NavHostController): Action()
    }
}