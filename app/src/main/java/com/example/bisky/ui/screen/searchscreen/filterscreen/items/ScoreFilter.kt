package com.example.bisky.ui.screen.searchscreen.filterscreen.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
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
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.ScoreFilterUI

@Composable
fun ScoreFilter(
    scoreRange: ScoreFilterUI,
    onScoreSelected: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.bisky_dark_400))
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Row {
            Text(
                text = stringResource(id = R.string.score_filter),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 4.dp, 4.dp)
                    .fillMaxWidth()
                    .weight(1.0f)
                ,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                letterSpacing = (-0.02).sp,
                color = colorResource(R.color.light_100)
            )
            Text(
                text = scoreRange.text,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 4.dp, 4.dp)
                    .align(Alignment.Bottom)
                ,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                letterSpacing = (-0.02).sp,
                color = colorResource(R.color.light_400)
            )
        }
        RangeSlider(
            value = scoreRange.scoreRange,
            steps = 9,
            onValueChange = { range ->
                if (range.start == range.endInclusive) return@RangeSlider
                onScoreSelected(range)
            },
            colors = SliderDefaults.colors(
                activeTrackColor = colorResource(id = R.color.bisky_200),
                thumbColor = colorResource(id = R.color.bisky_400),
            ),
            valueRange = 0f..10f,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ScoreFilter() {
    ScoreFilter(
        ScoreFilterUI((0f..10f),"Неважно"),
        {range-> }
    )
}
