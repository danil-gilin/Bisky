package com.example.bisky.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ItemLoader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(bottom = 25.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .height(20.dp)
                .align(Alignment.CenterHorizontally)
                .aspectRatio(1f)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun ItemLoaderPreview() {
    ItemLoader()
}
