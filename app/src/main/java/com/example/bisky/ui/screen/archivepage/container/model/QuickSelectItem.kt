package com.example.bisky.ui.screen.archivepage.container.model

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.example.bisky.common.model.BaseItem
import com.example.bisky.ui.screen.searchpage.searchrootscreen.model.SearchUI

@OptIn(ExperimentalFoundationApi::class)
data class QuickSelectItem(
    override val itemId: String = DEFAULT_ID,
    val isSearchVisible: Boolean = false,
    val searchTextField: TextFieldState = TextFieldState(""),
    val searchUI: SearchUI = SearchUI()
): BaseItem {
    companion object {
        const val DEFAULT_ID = "quick_select_item_id"
    }
}
