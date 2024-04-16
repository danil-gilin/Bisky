package com.example.bisky.ui.screen.userspage.userhomesscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bisky.R
import com.example.bisky.ui.screen.userspage.userhomesscreen.UserHomeView.Event
import com.example.bisky.ui.screen.userspage.userhomesscreen.UserHomeView.State
import kotlinx.coroutines.Dispatchers

@Composable
fun UserHomeScreen(
    navController: NavController,
    viewModel: UserHomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    UserHomeScreen(
        uiState,
        onLogoutClick = {
            viewModel.onEvent(Event.OnLogoutClick)
        }
    )
}

@Composable
fun UserHomeScreen(
    uiState: State,
    onLogoutClick:() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bisky_dark_400))
            .padding(top = 16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.profile_test_img)
                .fetcherDispatcher(Dispatchers.IO)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(45.dp))
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.CenterHorizontally)
                .clip(shape = RoundedCornerShape(8.dp)),
            placeholder = painterResource(id = R.drawable.ic_logo),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 32.dp),
            onClick = {
                onLogoutClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.bisky_300)
            ),
            shape = RoundedCornerShape(4.dp, 4.dp, 4.dp, 4.dp)
        ) {
            Text(text = stringResource(id = R.string.sigOut))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UserHomeScreenPreview(
) {
    UserHomeScreen(
        State(),
        {}
    )
}