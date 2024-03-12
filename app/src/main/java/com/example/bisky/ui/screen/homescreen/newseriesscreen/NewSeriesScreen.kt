package com.example.bisky.ui.screen.homescreen.newseriesscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bisky.R

@Composable
fun NewSeriesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bisky_dark_400))
    ) {
        Text(
            text = "In Progress Develop",
            color = colorResource(id = R.color.light_100),
            modifier = Modifier
                .align(alignment = Alignment.Center)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun NewSeriesScreenPreview() {
    NewSeriesScreen()
}
