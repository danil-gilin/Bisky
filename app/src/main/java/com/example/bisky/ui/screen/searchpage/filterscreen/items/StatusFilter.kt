package com.example.bisky.ui.screen.searchpage.filterscreen.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.searchpage.filterscreen.model.StatusAnimeFilter

@Composable
fun StatusFilter(
    selectedStatus: List<StatusAnimeFilter>,
    clearFilter: () -> Unit,
    onSelectStatus: (StatusAnimeFilter, Boolean) -> Unit,
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
                text = stringResource(id = R.string.status_filter),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 4.dp, 16.dp)
                    .weight(1.0f)
                    .fillMaxWidth()
                ,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                letterSpacing = (-0.02).sp,
                color = colorResource(R.color.light_100)
            )
            Text(
                text = stringResource(id = R.string.btn_filter_clear),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 4.dp, 16.dp)
                    .align(Alignment.Bottom)
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorResource(id = R.color.bisky_400))
                    .padding(4.dp,2.dp,4.dp,2.dp)
                    .noRippleClickable {
                        clearFilter()
                    }
                ,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.W700,
                letterSpacing = (-0.02).sp,
                color = colorResource(R.color.light_100)
            )
        }
        Column(
            Modifier
                .selectableGroup()
                .clip(RoundedCornerShape(20.dp))
                .background(colorResource(id = R.color.bisky_dark_200))
        )
        {
            CheckBoxWithText(
                status = StatusAnimeFilter.ANNOUNCED,
                isSelected = selectedStatus.contains(StatusAnimeFilter.ANNOUNCED),
                text = stringResource(id = R.string.status_filter_announced),
                onSelectStatus = onSelectStatus
            )
            CheckBoxWithText(
                status = StatusAnimeFilter.RELEASED,
                isSelected = selectedStatus.contains(StatusAnimeFilter.RELEASED),
                text = stringResource(id = R.string.status_filter_released),
                onSelectStatus = onSelectStatus
            )
            CheckBoxWithText(
                status = StatusAnimeFilter.PUBLISHED,
                isSelected = selectedStatus.contains(StatusAnimeFilter.PUBLISHED),
                text = stringResource(id = R.string.status_filter_published),
                onSelectStatus = onSelectStatus
            )
            CheckBoxWithText(
                status = StatusAnimeFilter.NOW,
                isSelected = selectedStatus.contains(StatusAnimeFilter.NOW),
                text = stringResource(id = R.string.status_filter_now),
                onSelectStatus = onSelectStatus
            )

        }
    }
}

@Composable
private fun CheckBoxWithText(
    status: StatusAnimeFilter,
    isSelected: Boolean,
    text: String,
    onSelectStatus: (StatusAnimeFilter, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val img = if (isSelected) {
        R.drawable.ic_checkbox_check
    } else {
        R.drawable.ic_checkbox_uncheck
    }
    Row(
        modifier = modifier
            .padding(
                end = 16.dp,
                start = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(0.dp, 0.dp, 4.dp, 0.dp)
                .align(Alignment.CenterVertically)
                .weight(1.0f)
                .fillMaxWidth(),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_100)
        )
        Image(
            painter = painterResource(id = img),
            contentDescription = "checkBox",
            modifier = Modifier.noRippleClickable {
                onSelectStatus(status, !isSelected)
            }
        )
    }
}


@Composable
@Preview(showBackground = true)
fun StatusFilterPreview() {
    StatusFilter(
        listOf(StatusAnimeFilter.PUBLISHED, StatusAnimeFilter.NOW),
        {},
        onSelectStatus = {it, ist ->}
    )
}

@Composable
@Preview(showBackground = true)
fun CheckBoxWithTextPreview() {
    CheckBoxWithText(
        StatusAnimeFilter.PUBLISHED,
        true,
        "Недавно вышедшее",
        {it, ist ->}
    )
}