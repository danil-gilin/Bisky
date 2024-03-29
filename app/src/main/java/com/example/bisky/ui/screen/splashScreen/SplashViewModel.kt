package com.example.bisky.ui.screen.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.bisky.data.network.resultwrapper.onError
import com.example.bisky.data.network.resultwrapper.onSuccess
import com.example.bisky.domain.repository.login.LoginRepository
import com.example.bisky.ui.navigation.ext.NavigationRoute.BoardingLogin
import com.example.bisky.ui.navigation.ext.NavigationRoute.Home
import com.example.bisky.ui.navigation.ext.NavigationRoute.Splash
import com.example.bisky.ui.screen.splashScreen.SplashView.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginRepositoryImpl: LoginRepository
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
