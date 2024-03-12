package com.example.bisky.ui.screen.loginScreen.boardingScreen

import androidx.compose.animation.EnterTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.ui.elements.BaseTextInput
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.loginScreen.siginscreen.SigInView
import com.example.bisky.ui.screen.loginScreen.siginscreen.SigInViewModel

@Composable
fun BoardingScreen(
    viewModel: BoardingViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()
    BoardingScreen(
        uiState = uiState,
        onSigInClicked = {
            navController.navigate("sigIn")
        },
        onSigUpClicked = {
            navController.navigate("sigUp")
        }
    )
}

@Composable
fun BoardingScreen(
    uiState: BoardingView.State,
    onSigInClicked: () -> Unit,
    onSigUpClicked: () -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    ConstraintLayout(
        modifier =
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bisky_dark_400))
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {
        val (logo, sigInFields) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(sigInFields.top)
                }
                .width(120.dp)
                .height(120.dp)
        )
        Box(
            modifier = Modifier
                .constrainAs(sigInFields) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
            SigInInputFields(
                uiState,
                onSigInClicked = {onSigInClicked()},
                onSigUpClicked = {onSigUpClicked()},
            )
        }
    }
}

@Composable
fun SigInInputFields(
    uiState: BoardingView.State,
    onSigInClicked: () -> Unit,
    onSigUpClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(
                colorResource(id = R.color.light_100),
                shape = RoundedCornerShape(
                    8.dp, 8.dp, 0.dp, 0.dp
                )
            )
            .padding(bottom = 24.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            onClick = {
                onSigInClicked()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.bisky_dark_300)
            ),
            shape = RoundedCornerShape(4.dp,4.dp,4.dp,4.dp)
        )
        {
            Text(text = stringResource(id = R.string.sigIn))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
                .padding(16.dp, 0.dp),
            onClick = {
                onSigUpClicked()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.bisky_dark_300)
            ),
            shape = RoundedCornerShape(4.dp,4.dp,4.dp,4.dp)
        )
        {
            Text(text = stringResource(id = R.string.sigUp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoardingScreenPreview() {
    BoardingScreen(
        BoardingView.State(),
        onSigInClicked = {},
        onSigUpClicked = {}
    )
}
