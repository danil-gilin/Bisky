package com.example.bisky.ui.screen.searchpage.filterscreen.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bisky.R
import com.example.bisky.ui.screen.searchpage.filterscreen.model.SortAnimeFilter

@Composable
fun SortedFilter(
    selectedSort: SortAnimeFilter,
    onSelectSort: (SortAnimeFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.bisky_dark_400))
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.sort_filter),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(0.dp, 0.dp, 4.dp, 4.dp),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_100)
        )
        Column(
            Modifier
                .selectableGroup()
                .background(colorResource(id = R.color.bisky_dark_400))
        )
        {
            RadioBtnWithText(
                status = SortAnimeFilter.RATING,
                isSelected = selectedSort == SortAnimeFilter.RATING,
                text = stringResource(id = R.string.sort_filter_rating),
                onSelectStatus = onSelectSort
            )
            RadioBtnWithText(
                status = SortAnimeFilter.POPULATION,
                isSelected = selectedSort == SortAnimeFilter.POPULATION,
                text = stringResource(id = R.string.sort_filter_popularity),
                onSelectStatus = onSelectSort
            )
            RadioBtnWithText(
                status = SortAnimeFilter.WORDS,
                isSelected = selectedSort == SortAnimeFilter.WORDS,
                text = stringResource(id = R.string.sort_filter_words),
                onSelectStatus = onSelectSort
            )

        }
    }
}

@Composable
private fun RadioBtnWithText(
    status: SortAnimeFilter,
    isSelected: Boolean,
    text: String,
    onSelectStatus: (SortAnimeFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(16.dp, 0.dp, 4.dp, 0.dp)
                .align(Alignment.CenterVertically)
                .weight(1.0f)
                .fillMaxWidth(),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_100)
        )
        RadioButton(
            selected = isSelected,
            onClick = { onSelectStatus(status) },
            colors = RadioButtonDefaults
                .colors(
                    selectedColor = colorResource(id = R.color.bisky_200),
                    unselectedColor = colorResource(id = R.color.bisky_200)
                )
        )
    }
}


@Composable
@Preview(showBackground = true)
fun SortedFilterPreview() {
    SortedFilter(
        SortAnimeFilter.WORDS,
        onSelectSort = {itt ->}
    )
}

@Composable
@Preview(showBackground = true)
fun RadioButtonWithTextPreview() {
    RadioBtnWithText(
        SortAnimeFilter.POPULATION,
        true,
        stringResource(id = R.string.sort_filter_rating),
        {it ->}
    )
}