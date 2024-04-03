package com.example.bisky.ui.screen.searchscreen.filterscreen.mapper

import com.example.bisky.domain.repository.genre.model.GenreSimple
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.GenreFilterUI
import javax.inject.Inject

class FilterMapper @Inject constructor() {

    fun mapGenreToUI(
        listGenre: List<GenreSimple>,
        selectedGenreId: List<String>
    ) = listGenre.mapNotNull {
        if (it.name.isNullOrEmpty()) return@mapNotNull null
        GenreFilterUI(
            id = it.id,
            name = it.name,
            isSelected = selectedGenreId.contains(it.id)
        )
    }
}