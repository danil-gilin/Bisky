package com.example.bisky.ui.screen.animescreen.items.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.animescreen.model.body.AnimeDescriptionUI

@Composable
fun AnimeDescriptionItems(
    animeDescriptionUI: AnimeDescriptionUI,
    onClickMoreInfo: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = colorResource(id = R.color.bisky_dark_400))
            .padding(start = 8.dp, end = 8.dp)
    ) {
        val text = if (animeDescriptionUI.isFullInfo) {
            animeDescriptionUI.text
        } else {
            animeDescriptionUI.text.substring(0, 250) + "..."
        }
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.light_300)
        )
        if (!animeDescriptionUI.isFullInfo) {
            Text(
                text = stringResource(id = R.string.anime_full_description),
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .noRippleClickable { onClickMoreInfo(true) },
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.light_400)
            )
        } else {
            Text(
                text = stringResource(id = R.string.anime_small_description),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .noRippleClickable { onClickMoreInfo(false) },
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.light_400)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AnimeDescriptionItemsPreview() {

    AnimeDescriptionItems(
        AnimeDescriptionUI(
            itemId = "itemsId",
            isFullInfo = false,
            text = stringResource(id = R.string.long_text)
        ),
        onClickMoreInfo = {

        }
    )
}
