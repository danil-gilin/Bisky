package com.example.bisky.ui.screen.splashScreen


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bisky.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavHostController) {
    val shadow = remember { Animatable(450f) }
    val scale = remember { Animatable(1.2f) }

    LaunchedEffect(key1 = true) {
        coroutineScope {
            launch {
                shadow.animateTo(
                    targetValue = 150f,
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = LinearEasing
                    )
                )
            }
            launch {
                scale.animateTo(
                    targetValue = 0.9f,
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = LinearEasing
                    )
                )
            }
            delay(1000L)
            navController.navigate("home")
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bisky_dark_400)) // Change color as per your requirement
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value,
                    shadowElevation = shadow.value,
                    ambientShadowColor = colorResource(id = R.color.bisky_100),
                    spotShadowColor = colorResource(id = R.color.bisky_200),
                    shape = CircleShape
                )
                .size(200.dp) // Set your desired size
                .padding(50.dp,25.dp,50.dp,25.dp)

        )
    }
}