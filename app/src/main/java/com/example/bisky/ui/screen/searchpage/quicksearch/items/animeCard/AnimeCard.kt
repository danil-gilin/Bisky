package com.example.bisky.ui.screen.searchpage.quicksearch.items.animeCard

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bisky.ui.screen.searchpage.quicksearch.model.AnimeFrontInfoUI
import com.example.bisky.ui.screen.searchpage.quicksearch.model.AnimeBackInfoUI
import com.wajahatkarim.flippable.FlipAnimationType
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.rememberFlipController

@Composable
fun AnimeCard(
    frontSide: AnimeFrontInfoUI?,
    backSide: AnimeBackInfoUI?,
    onClickMoreInfo: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Flippable(
        modifier = modifier,
        flipAnimationType = FlipAnimationType.HORIZONTAL_ANTI_CLOCKWISE,
        flipDurationMs = 600,
        frontSide = {
            frontSide?.let {
                AnimePreviewCard(
                    it,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                )
            }
        },

        backSide = {
            backSide?.let {
                AnimeFullInfoCard(
                    it,
                    onClickMoreInfo,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                )
            }
        },

        flipController = rememberFlipController(),
    )
}


@Composable
@Preview(showBackground = true)
fun AnimeCardPreview() {
    AnimeCard(
        AnimeFrontInfoUI.previewItemAnimeFront,
        AnimeBackInfoUI.previewItemAnimeBackInfo,
        {}
    )
}