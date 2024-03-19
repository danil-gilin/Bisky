package com.example.bisky.ui.screen.homescreen.genre.genresscreen.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable

@Composable
fun HeaderGenre(
    title: String,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .background(colorResource(id = R.color.bisky_dark_400_alpha_80))
            .padding(top = 4.dp, bottom = 4.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "close",
            modifier = Modifier
                .align(Alignment.Top)
                .padding(start = 16.dp, end = 0.dp, top = 12.dp)
                .noRippleClickable {
                    onBackClick()
                }
            ,
            tint = colorResource(id = R.color.white)
        )
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, end = 45.dp, start = 8.dp)
            ,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.white)
            )
    }
}


@Composable
@Preview(showBackground = true)
fun HeaderGenrePreview() {
    HeaderGenre("Genre Genre Genre GenreGenreGenre GenreGenreGenre", {})
}