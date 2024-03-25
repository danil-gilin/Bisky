package com.example.bisky.ui.screen.animescreen.items.body

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bisky.R
import com.example.bisky.ui.screen.animescreen.model.body.AnimeProducerInfoUI

@Composable
fun AnimeProducerInfoItem(
    animeProducerInfoUI: AnimeProducerInfoUI
) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.bisky_dark_400))
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
    ) {
        TextItem(animeProducerInfoUI.genres, R.drawable.ic_genre)
        if (animeProducerInfoUI.isProducerVisible) {
            TextItem(animeProducerInfoUI.producer, R.drawable.ic_paint)
        }
    }
}

@Composable
private fun TextItem(text: String, icon: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(4.dp, 0.dp, 4.dp, 0.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            modifier = Modifier
                .padding(2.dp, 0.dp, 4.dp, 0.dp)
                .height(18.dp)
                .width(18.dp)
                .align(Alignment.Top),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = text,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(2.dp, 0.dp, 4.dp, 4.dp),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_200)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AnimeProducerInfoItemPreview() {

    AnimeProducerInfoItem(
        AnimeProducerInfoUI(
            itemId = "itemsId",
            genres = "anime / aneme / anime",
            producer = "Daniel Gilin",
            isProducerVisible = true
        )
    )
}