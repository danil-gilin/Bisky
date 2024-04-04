package com.example.bisky.ui.screen.searchscreen.searchrootscreen.items

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text2.input.TextFieldState
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
import com.example.bisky.ui.elements.BaseTextSearch
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.loginScreen.siginscreen.model.TextSigInUI
import com.example.bisky.ui.screen.searchscreen.searchrootscreen.model.SearchUI

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeaderSearch(
    onSearchClick: () -> Unit,
    onFilterClick: () -> Unit,
    searchInputVisible: Boolean,
    searchTextField: TextFieldState,
    searchUI: SearchUI
) {
    val tintSearch = if (searchInputVisible){
        R.color.bisky_100
    } else {
        R.color.light_200
    }.let {
        colorResource(it)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.bisky_dark_400))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.bisky_dark_400))
                .padding(top = 4.dp, bottom = 4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "close",
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(start = 16.dp, end = 0.dp, top = 6.dp),
            )
            if (searchInputVisible) {
                BaseTextSearch(
                    isPlaceHolderVisible = searchUI.isPlaceHolderVisible,
                    isClearIconVisible = searchUI.isClearIconVisible,
                    state = searchTextField,
                    placeHolder = stringResource(id = searchUI.placeHolder),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth()
                        .weight(1.0f)
                        .padding(top = 0.dp, bottom = 8.dp, end = 8.dp, start = 8.dp),
                )
            } else {
                Text(
                    text = stringResource(id = R.string.search_title),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth()
                        .weight(1.0f)
                        .padding(top = 8.dp, bottom = 8.dp, end = 45.dp, start = 8.dp),
                    textAlign = TextAlign.Left,
                    color = colorResource(id = R.color.white)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_search_action),
                contentDescription = "search",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .wrapContentWidth()
                    .padding(start = 0.dp, end = 16.dp, top = 0.dp)
                    .noRippleClickable { onSearchClick() }
                ,
                tint = tintSearch
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_filter),
            contentDescription = "search",
            modifier = Modifier
                .align(Alignment.End)
                .wrapContentWidth()
                .padding(start = 0.dp, end = 16.dp, top = 8.dp)
                .noRippleClickable { onFilterClick() }
            ,
            tint = colorResource(id = R.color.light_200)
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun HeaderGenrePreview() {
    HeaderSearch(
        onSearchClick = {},
        onFilterClick = {},
        searchInputVisible = false,
        searchTextField = TextFieldState("dfsdf"),
        searchUI = SearchUI()
    )
}