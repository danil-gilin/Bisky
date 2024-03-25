package com.example.bisky.ui.screen.animescreen.items.body

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bisky.R
import com.example.bisky.ui.screen.animescreen.model.body.AnimeUserListUI

@Composable
fun AnimeUserListItem(
    animeUserListUI: AnimeUserListUI
) {
    Column {
        Text(
            text = stringResource(id = R.string.anime_user_list),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(16.dp, 0.dp, 4.dp, 16.dp),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_100)
        )
        Row(
            modifier = Modifier
                .background(color = colorResource(id = R.color.bisky_dark_400))
                .padding(start = 16.dp)
        ) {
            CircleInfo(
                animeUserListUI, modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                TextItem(
                    stringResource(id = R.string.watching),
                    R.drawable.ic_play,
                    animeUserListUI.watchingCount,
                    R.color.blue
                )
                TextItem(
                    stringResource(id = R.string.added),
                    R.drawable.ic_archive,
                    animeUserListUI.addedCount,
                    R.color.bisky_200
                )
                TextItem(
                    stringResource(id = R.string.completed),
                    R.drawable.ic_check,
                    animeUserListUI.completedCount,
                    R.color.green
                )
                TextItem(
                    stringResource(id = R.string.dropped),
                    R.drawable.ic_gabbage,
                    animeUserListUI.droppedCount,
                    R.color.bisky_dark_100
                )
                TextItem(
                    stringResource(id = R.string.user_list_all_count),
                    null,
                    animeUserListUI.generalCount,
                    R.color.gray
                )
            }
        }
    }

}

@Composable
private fun TextItem(
    text: String,
    icon: Int?,
    count: Int,
    textColor: Int
) {
    val text = "$text: $count"
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(4.dp, 0.dp, 4.dp, 0.dp)
    ) {
        if (icon != null) {
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
        }
        Text(
            text = text,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(2.dp, 0.dp, 4.dp, 4.dp),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(textColor)
        )
    }
}

@Composable
fun CircleInfo(
    animeUserListUI: AnimeUserListUI,
    modifier: Modifier
) {
    val colorBackground = colorResource(id = R.color.bisky_dark_400)
    val green = colorResource(id = R.color.green)
    val blue = colorResource(id = R.color.blue)
    val pink = colorResource(id = R.color.bisky_200)
    val dark = colorResource(id = R.color.bisky_dark_100)

    val watching = animeUserListUI.watchingCount
    val added = animeUserListUI.addedCount
    val completed = animeUserListUI.completedCount
    val dropped = animeUserListUI.droppedCount

    val generalCount = watching + added + completed + dropped

    val watchingPercent = watching.toFloat() / generalCount
    val completedPercent = completed.toFloat() / generalCount
    val addedPercent = added.toFloat() / generalCount
    val droppedPercent = dropped.toFloat() / generalCount

    Surface(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .size(100.dp)
                .background(colorBackground),
            onDraw = {
                drawArc(
                    color = blue,
                    0.0F,
                    sweepAngle = (watchingPercent * 360),
                    true
                )
                drawArc(
                    color = green,
                    startAngle = (watchingPercent * 360),
                    sweepAngle = (completedPercent * 360),
                    true
                )
                drawArc(
                    color = pink,
                    startAngle = (watchingPercent * 360) + (completedPercent * 360),
                    sweepAngle = (addedPercent * 360),
                    true
                )
                drawArc(
                    color = dark,
                    startAngle = (watchingPercent * 360) + (completedPercent * 360) + (addedPercent * 360),
                    sweepAngle = (droppedPercent * 360),
                    true
                )
                drawCircle(
                    color = colorBackground,
                    radius = 100f
                )
                drawCircle(
                    color = colorBackground,
                    radius = 100f
                )
            }
        )
    }
}


@Composable()
@Preview(showBackground = true)
fun AnimeUserListPreview() {
    AnimeUserListItem(
        AnimeUserListUI(
            itemId = "sad",
            addedCount = 23,
            completedCount = 22,
            droppedCount = 64,
            watchingCount = 143,
            generalCount = 291
        )
    )
}