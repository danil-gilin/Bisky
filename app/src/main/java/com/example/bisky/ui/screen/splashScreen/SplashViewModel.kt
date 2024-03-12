package com.example.bisky.ui.screen.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.bisky.data.login.LoginRepositoryImpl
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.ui.navigation.NavigationRoute.BoardingLogin
import com.example.bisky.ui.navigation.NavigationRoute.Home
import com.example.bisky.ui.navigation.NavigationRoute.Splash
import com.example.bisky.ui.screen.splashScreen.SplashView.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginRepositoryImpl: LoginRepositoryImpl
) : ViewModel() {

    fun onAction(action: Action) {
        when (action) {
            is Action.CheckSigIn -> checkSigIn(action.navHostController)
        }
    }

    private fun checkSigIn(navHostController: NavHostController) = viewModelScope.launch {
        loginRepositoryImpl.checkSigIn().onSuccess {
            navHostController.navigate(Home.route) {
                popUpTo(Splash.route) {
                    inclusive = true
                }
            }
        }.onError {
            navHostController.navigate(BoardingLogin.route) {
                popUpTo(Splash.route) {
                    inclusive = true
                }
            }
        }
    }
}