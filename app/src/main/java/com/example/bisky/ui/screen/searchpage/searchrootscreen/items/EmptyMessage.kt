package com.example.bisky.ui.screen.searchpage.searchrootscreen.items

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bisky.R

@Composable
fun EmptyMessage(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.search_empty),
        fontSize = 16.sp,
        modifier = modifier
            .padding(16.dp, 16.dp, 4.dp, 16.dp),
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W700,
        letterSpacing = (-0.02).sp,
        color = colorResource(R.color.light_100)
    )
}

@Composable
@Preview(showBackground = true)
fun EmptyMessagePreview() {
    EmptyMessage()
}
