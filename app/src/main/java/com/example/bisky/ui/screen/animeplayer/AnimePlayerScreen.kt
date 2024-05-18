package com.example.bisky.ui.screen.animeplayer

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.ui.screen.animeplayer.AnimePlayerView.State

@Composable
fun AnimePlayerScreen(
    navController: NavController,
    viewModel: AnimePlayerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    AnimePlayerScreen(
        uiState,
        viewModel,
        onBackClick = {
            viewModel.onAction(AnimePlayerView.Action.OnBackClick)
            navController.popBackStack()
        },
    )
}

@Composable
fun AnimePlayerScreen(
    uiState: State,
    viewModel: AnimePlayerViewModel ,
    onBackClick: () -> Unit
) {
    BackHandler {
        onBackClick()
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.bisky_dark_400))
    ) {
        Player(uiState.url, viewModel)
    }
}

@Composable
fun Player (url: String, viewModel: AnimePlayerViewModel) {
    AndroidView(
        factory = { context ->
            viewModel.webView ?: WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                setBackgroundColor(context.getColor(R.color.bisky_dark_400))

                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(false)

                loadUrl(url)
                viewModel.webView = this
            }
        },
        update = { webView ->
            if (webView.url != url) {
                webView.loadUrl(url)
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
@Preview(showBackground = true)
fun AnimePlayerScreenPreview() {
}