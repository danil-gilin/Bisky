package com.example.bisky.ui.screen.searchpage.searchrootscreen.mapper

import com.example.bisky.R
import com.example.bisky.ui.screen.searchpage.searchrootscreen.model.SearchUI
import javax.inject.Inject

class TextSearchUIMapper@Inject constructor() {
    fun map(text: String): SearchUI {
        return SearchUI(
            isClearIconVisible = text.isNotEmpty(),
            isPlaceHolderVisible = text.isEmpty(),
            placeHolder = R.string.search_placeholder
        )
    }
}
