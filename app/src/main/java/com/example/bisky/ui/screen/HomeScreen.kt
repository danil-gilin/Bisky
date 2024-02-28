package com.example.bisky.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel()
) {
    val uiState by homeViewModel.uiState.collectAsState()
    HomeScreen(
        uiState = uiState
    )
}

@Composable
fun HomeScreen(
    uiState: HomeScreenView.State
) {
    Column {
    }
}

@Composable
fun EnableFeature(
    enabled: Boolean,
    onEnabledChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = CenterVertically) {
        Checkbox(checked = enabled, onCheckedChange = onEnabledChange)
        Text("enable feature")
    }
}

@Composable
fun ClickCounter(
    count: Int,
    onCounterClick: () -> Unit
) {
    Text(
        text = "Clicks: $count",
        modifier = Modifier.clickable(onClick = onCounterClick)
    )
}

@Preview(showBackground = true)
@Composable
fun ClickCounterPreview() {
    ClickCounter(
        count = 5,
        onCounterClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

class Counter {
    var value = 0
}