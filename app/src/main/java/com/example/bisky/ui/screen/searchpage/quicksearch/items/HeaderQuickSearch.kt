package com.example.bisky.ui.screen.searchpage.quicksearch.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable

@Composable
fun HeaderQuickSearch(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.bisky_dark_400))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "close",
            modifier = Modifier
                .padding(start = 16.dp, end = 0.dp, top = 2.dp)
                .align(Alignment.CenterStart)
                .noRippleClickable {
                    onBackClick()
                }
            ,
            tint = colorResource(id = R.color.white)
        )
        Row(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.quick_search_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, end = 0.dp, start = 8.dp),
                textAlign = TextAlign.Left,
                color = colorResource(id = R.color.white)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "close",
                modifier = Modifier
                    .padding(start = 8.dp, end = 0.dp, top = 4.dp)
                    .align(Alignment.CenterVertically)
                    .noRippleClickable {
                        onBackClick()
                    }
                ,
                tint = colorResource(id = R.color.white)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HeaderQuickSearchPreview() {
    HeaderQuickSearch(
        onBackClick={}
    )
}