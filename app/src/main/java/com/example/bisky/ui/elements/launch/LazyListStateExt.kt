package com.example.bisky.ui.elements.launch

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import com.example.bisky.ui.elements.isScrolledToEnd
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@Composable
inline fun LazyListState.LaunchAtTheEndOfList(
    crossinline onGetMore: () -> Unit
) {
    val isAtTheEndOfList by remember(this) {
        derivedStateOf {
            this.isScrolledToEnd()
        }
    }
    LaunchedEffect(isAtTheEndOfList) {
        if (isAtTheEndOfList) onGetMore()
    }
}

@Composable
inline fun LazyGridState.LaunchAtTheEndOfGrid(
    crossinline onGetMore: () -> Unit
) {
    val isAtTheEndOfList by remember(this) {
        derivedStateOf {
            this.isScrolledToEnd()
        }
    }
    LaunchedEffect(isAtTheEndOfList) {
        if (isAtTheEndOfList) onGetMore()
    }
}

@OptIn(FlowPreview::class)
@Composable
inline fun lazyListStateWithListenerScroll(
    positionScroll: Int,
    crossinline onScrollItem: (Int) -> Unit
): LazyListState {
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = positionScroll)
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.firstVisibleItemIndex
        }
            .debounce(500L)
            .collectLatest {
                onScrollItem(it)
            }
    }
    return listState
}

@OptIn(FlowPreview::class)
@Composable
inline fun lazyGridStateWithListenerScroll(
    positionScroll: Int,
    crossinline onScrollItem: (Int) -> Unit
): LazyGridState {
    val gridState = rememberLazyGridState(initialFirstVisibleItemIndex = positionScroll)
    LaunchedEffect(gridState) {
        snapshotFlow {
            gridState.firstVisibleItemIndex
        }
            .debounce(500L)
            .collectLatest {
                onScrollItem(it)
            }
    }
    return gridState
}

