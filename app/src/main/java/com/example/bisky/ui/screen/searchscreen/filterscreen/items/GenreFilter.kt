package com.example.bisky.ui.screen.searchscreen.filterscreen.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.GenreFilterUI

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenreFilter(
    listGenre: List<GenreFilterUI>,
    onGenreSelected: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.bisky_dark_400))
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.genre_filter),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(0.dp, 0.dp, 4.dp, 4.dp),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_100)
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier
                .padding(7.dp)
        ) {
            listGenre.forEach { genre ->
                val backGroundColor = if (genre.isSelected) {
                    R.color.bisky_100
                } else {
                    R.color.bisky_400_alpha_70
                }
                Text(
                    genre.name,
                    modifier = Modifier
                        .background(
                            color = colorResource(id = backGroundColor),
                            shape = CircleShape
                        )
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .noRippleClickable {
                            onGenreSelected(genre.id, !genre.isSelected)
                        }
                    ,
                    color = colorResource(id = R.color.light_100)
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun GenreFilterPreview() {
    GenreFilter(
        listOf(
            GenreFilterUI(
                id = "1",
                name = "Anime", isSelected = false
            ),
            GenreFilterUI(
                id = "1",
                name = "Anime2", isSelected = true
            ),
            GenreFilterUI(
                id = "1",
                name = "Anime3", isSelected = false
            ),
            GenreFilterUI(
                id = "1",
                name = "Anime", isSelected = true
            ),
            GenreFilterUI(
                id = "1",
                name = "Anime", isSelected = false
            ),
            GenreFilterUI(
                id = "1",
                name = "Anime", isSelected = false
            ),
            GenreFilterUI(
                id = "1",
                name = "Anime", isSelected = false
            ),
            GenreFilterUI(
                id = "1",
                name = "Anime", isSelected = false
            ),
            GenreFilterUI(
                id = "1",
                name = "Anime", isSelected = false
            ),
            GenreFilterUI(
                id = "1",
                name = "Anime", isSelected = false
            ),
            GenreFilterUI(
                id = "1",
                name = "Anime", isSelected = false
            )
        ),
        onGenreSelected = { it, dfs -> }
    )
}