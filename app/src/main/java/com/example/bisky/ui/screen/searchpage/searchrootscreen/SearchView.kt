package com.example.bisky.ui.screen.searchpage.searchrootscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.example.bisky.R
import com.example.bisky.common.model.BaseItem
import com.example.bisky.ui.screen.searchpage.searchrootscreen.model.SearchUI

interface SearchView {
    @OptIn(ExperimentalFoundationApi::class)
    data class State(
        val isLoading: Boolean = false,
        val isEmptyResult: Boolean = false,
        val items: List<BaseItem> = emptyList(),
        val positionScroll: Int = 0,
        val isSearchInputVisible : Boolean = false,
        val searchTextField: TextFieldState = TextFieldState(),
        val searchUI: SearchUI = SearchUI(placeHolder = R.string.search_placeholder),
    )

    sealed class Event {
        data class OnScrollItem(val position: Int) : Event()
        data object OnGetMore : Event()
        data object OnSearchClick : Event()
    }
}