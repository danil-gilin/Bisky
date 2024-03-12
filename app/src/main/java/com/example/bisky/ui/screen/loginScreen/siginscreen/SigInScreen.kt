package com.example.bisky.ui.screen.loginScreen.siginscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.ui.elements.BaseTextInput
import com.example.bisky.ui.elements.noRippleClickable

@Composable
fun SigInScreen(
    viewModel: SigInViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()
    SigInScreen(
        uiState = uiState,
        onBackClicked = {
            navController.popBackStack()
        },
        onSigInClicked = {
            viewModel.onEvent(SigInView.Event.OnSigInBtnClick(navController))
        }
    )
}

@Composable
fun SigInScreen(
    uiState: SigInView.State,
    onBackClicked: () -> Unit,
    onSigInClicked: () -> Unit
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
                }
        ) {
            SigInInputFields(
                uiState,
                onBackClicked = { onBackClicked() },
                onSigInClicked = { onSigInClicked() }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SigInInputFields(
    uiState: SigInView.State,
    onBackClicked: () -> Unit,
    onSigInClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                colorResource(id = R.color.light_100),
                shape = RoundedCornerShape(
                    8.dp,
                    8.dp,
                    0.dp,
                    0.dp
                )
            )
    ) {
        BaseTextInput(
            uiState.loginTextField,
            stringResource(id = uiState.login.placeHolder),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp, 16.dp, 2.dp),
            colorBorder = uiState.login.borderColor,
            isClearIconVisible = uiState.login.isClearIconVisible,
            isPlaceHolderVisible = uiState.login.isPlaceHolderVisible
        )
        BaseTextInput(
            uiState.passwordTextField,
            stringResource(id = uiState.password.placeHolder),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp, 16.dp, 2.dp),
            colorBorder = uiState.password.borderColor,
            KeyboardType.Password,
            isClearIconVisible = uiState.password.isClearIconVisible,
            isPlaceHolderVisible = uiState.password.isPlaceHolderVisible
        )
        if (uiState.isErrorLogin) {
            Text(
                text = stringResource(id = R.string.validSigIn),
                color = colorResource(id = R.color.red),
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Row {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "back",
                tint = colorResource(id = R.color.bisky_200),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
                    .background(Color.Transparent)
                    .noRippleClickable {
                        onBackClicked()
                    }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp),
                onClick = {
                    onSigInClicked()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.bisky_dark_400)
                ),
                shape = RoundedCornerShape(4.dp, 4.dp, 4.dp, 4.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(20.dp)
                            .aspectRatio(1f)
                    )
                } else {
                    Text(text = stringResource(id = R.string.sigIn))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SigInInputPreview() {
    SigInInputFields(
        SigInView.State(),
        onBackClicked = {},
        onSigInClicked = {}
    )
}

@Composable
@Preview(showBackground = true)
fun SigInScreenPreview() {
    SigInScreen(
        SigInView.State(),
        onBackClicked = {},
        onSigInClicked = {}
    )
}
