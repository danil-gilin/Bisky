package com.example.bisky.ui.screen.archivepage.container.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.example.bisky.R
import com.example.bisky.ui.elements.BaseTextSearch
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.archivepage.container.model.QuickSelectItem
import com.example.bisky.ui.screen.searchpage.searchrootscreen.model.SearchUI


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuickSelectAnimeItem(
    onSearchClick: () -> Unit,
    onQuickSelectClick: () -> Unit,
    onFilterClick: () -> Unit,
    quickSelectItem: QuickSelectItem
) {
    val tintSearch = if (quickSelectItem.isSearchVisible) {
        R.color.bisky_100
    } else {
        R.color.light_200
    }.let {
        colorResource(it)
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(colorResource(id = R.color.bisky_dark_400))
            .padding(top = 4.dp, bottom = 4.dp, start = 16.dp)
    ) {
        val (btnQuickSearch, filter, ivSearch, edSearch) = createRefs()
        BaseTextSearch(
            isPlaceHolderVisible = quickSelectItem.searchUI.isPlaceHolderVisible,
            isClearIconVisible = quickSelectItem.searchUI.isClearIconVisible,
            state = quickSelectItem.searchTextField,
            placeHolder = stringResource(id = quickSelectItem.searchUI.placeHolder),
            modifier = Modifier
                .constrainAs(edSearch) {
                    visibility =
                        if (quickSelectItem.isSearchVisible) Visibility.Visible else Visibility.Gone
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    linkTo(parent.start, filter.start, bias = 0F)
                    end.linkTo(ivSearch.start, 16.dp)
                }
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 8.dp, end = 8.dp, start = 8.dp),
        )
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(colorResource(id = R.color.bisky_300))
                .constrainAs(btnQuickSearch) {
                    visibility =
                        if (quickSelectItem.isSearchVisible) Visibility.Gone else Visibility.Visible
                    top.linkTo(parent.top)
                    linkTo(parent.start, filter.start, bias = 0F)
                    bottom.linkTo(parent.bottom)
                }
                .noRippleClickable {
                    onQuickSelectClick()
                }
        ) {
            Text(
                text = stringResource(id = R.string.quick_select),
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = 8.dp, bottom = 8.dp, end = 8.dp, start = 45.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.white)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_king),
                contentDescription = "search",
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .padding(start = 4.dp, end = 48.dp, top = 0.dp),
                tint = colorResource(id = R.color.light_200)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search_action),
            contentDescription = "search",
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = 0.dp, end = 36.dp, top = 0.dp)
                .constrainAs(ivSearch) {
                    end.linkTo(filter.start)
                    top.linkTo(filter.top)
                    bottom.linkTo(filter.bottom)
                }
                .noRippleClickable { onSearchClick() },
            tint = tintSearch
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_filter),
            contentDescription = "search",
            modifier = Modifier
                .padding(start = 0.dp, end = 16.dp, top = 0.dp)
                .constrainAs(filter) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .noRippleClickable { onFilterClick() },
            tint = colorResource(id = R.color.light_200)
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun QuickSelectAnimeItemPreview() {
    QuickSelectAnimeItem(
        onSearchClick = {},
        onFilterClick = {},
        onQuickSelectClick = {},
        quickSelectItem = QuickSelectItem(
            "sdaasd",
            isSearchVisible = true,
            searchTextField = TextFieldState("dfsdf"),
            searchUI = SearchUI()
        )
    )
}