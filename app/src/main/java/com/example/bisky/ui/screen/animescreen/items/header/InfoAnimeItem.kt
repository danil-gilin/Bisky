package com.example.bisky.ui.screen.animescreen.items.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bisky.R
import com.example.bisky.domain.repository.anime.model.Collection
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.animescreen.model.header.InfoAnimeItemUI

@Composable
fun InfoAnimeItem(
    infoAnimeItemUI: InfoAnimeItemUI,
    onCollectionSelected: (Collection) -> Unit
) {
    Row(modifier = Modifier.padding(top = 8.dp)) {
        Column() {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                TextItem(infoAnimeItemUI.infoDuration, R.drawable.ic_player)
                TextStatusItem(
                    infoAnimeItemUI.infoType,
                    R.drawable.ic_clock,
                    infoAnimeItemUI.infoStatus,
                    infoAnimeItemUI.statusColor
                )
                TextItem(infoAnimeItemUI.infoDate, R.drawable.ic_calendar)
            }
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Row {
                    Collection(
                        infoAnimeItemUI.collectionNone,
                        Collection.NONE,
                        onCollectionSelected
                    )
                    Collection(
                        infoAnimeItemUI.collectionAdded,
                        Collection.ADDED,
                        onCollectionSelected
                    )
                    Collection(
                        infoAnimeItemUI.collectionWatching,
                        Collection.WATCHING,
                        onCollectionSelected
                    )
                    Collection(
                        infoAnimeItemUI.collectionCompleted,
                        Collection.COMPLETED,
                        onCollectionSelected
                    )
                    Collection(
                        infoAnimeItemUI.collectionDropped,
                        Collection.DROPPED,
                        onCollectionSelected
                    )
                }
            }
        }
    }
}

@Composable
private fun Collection(
    collectionIcon: Int,
    collection: Collection,
    onCollectionSelected: (Collection) -> Unit
) {
    Image(
        painter = painterResource(id = collectionIcon),
        modifier = Modifier
            .padding(4.dp, 4.dp, 0.dp, 4.dp)
            .height(35.dp)
            .width(35.dp)
            .noRippleClickable {
                onCollectionSelected(collection)
            },
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
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
                .height(12.dp)
                .width(12.dp),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = text,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(2.dp, 0.dp, 4.dp, 0.dp),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_400)
        )
    }
}

@Composable
private fun TextStatusItem(
    text: String,
    icon: Int,
    statusText: String,
    colorStatus: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(4.dp, 0.dp, 4.dp, 0.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            modifier = Modifier
                .padding(2.dp, 0.dp, 4.dp, 0.dp)
                .height(12.dp)
                .width(12.dp),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = text,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(2.dp, 0.dp, 0.dp, 0.dp),
            fontFamily = FontFamily.Default,
            letterSpacing = (-0.02).sp,
            fontWeight = FontWeight.W700,
            color = colorResource(R.color.light_400)
        )
        Text(
            text = statusText,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(4.dp, 0.dp, 4.dp, 0.dp),
            fontFamily = FontFamily.Default,
            letterSpacing = (-0.02).sp,
            fontWeight = FontWeight.W900,
            color = colorResource(colorStatus)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InfoAnimeItemPreview() {
    InfoAnimeItem(
        InfoAnimeItemUI(
            itemId = "info",
            infoDuration = "24 эп. по ~ 24 мин.",
            infoStatus = "вышел",
            statusColor = R.color.green,
            infoType = "Сериал,",
            infoDate = "Осень 2012 г.",
            collectionAdded = R.drawable.ic_added_collection,
            collectionCompleted = R.drawable.ic_completed_collection_disable,
            collectionDropped = R.drawable.ic_delete_collection_disable,
            collectionWatching = R.drawable.ic_play_collection_disable,
            collectionNone = R.drawable.ic_none_collection_disable
        ),
        {}
    )
}
