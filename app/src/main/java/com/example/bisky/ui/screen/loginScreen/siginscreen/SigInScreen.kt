package com.example.bisky.ui.screen.loginScreen.siginscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.bisky.ui.elements.BaseTextInputWith

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
            navController.navigate("home")
        }
    )
}

@Composable
fun SigInScreen(
    uiState: SigInView.State,
    onBackClicked: () -> Unit,
    onSigInClicked: () -> Unit,
) {
    ConstraintLayout(
        modifier =
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bisky_dark_400))
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
                onBackClicked = {onBackClicked()},
                onSigInClicked = {onSigInClicked()},
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SigInInputFields(
    uiState: SigInView.State,
    onBackClicked: () -> Unit,
    onSigInClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(
                colorResource(id = R.color.light_100),
                shape = RoundedCornerShape(
                    8.dp, 8.dp, 0.dp, 0.dp
                )
            )
    ) {
        BaseTextInputWith(
            uiState.email,
            stringResource(id = R.string.email_placeholder),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp, 16.dp, 8.dp),
            colorBorder = uiState.emailBorderColor
        )
        BaseTextInputWith(
            uiState.password,
            stringResource(id = R.string.password_placeholder),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp, 16.dp, 8.dp),
            colorBorder = uiState.passwordBorderColor,
            KeyboardType.Password
        )
        Row {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "back",
                tint = colorResource(id = R.color.bisky_200),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp, 0.dp, 0.dp, 0.dp)
                    .background(Color.Transparent)
                    .clickable {
                        onBackClicked()
                    }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp),
                onClick = {
                    onSigInClicked()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.bisky_dark_400)
                ),
                shape = RoundedCornerShape(4.dp,4.dp,4.dp,4.dp)
            )
            {
                Text(text = stringResource(id = R.string.sigIn))
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
