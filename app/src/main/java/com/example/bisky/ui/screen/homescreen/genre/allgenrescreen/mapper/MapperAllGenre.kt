package com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.mapper

import com.example.bisky.R
import com.example.bisky.domain.repository.genre.model.Genre
import com.example.bisky.ui.screen.homescreen.genre.allgenrescreen.model.GenreUI
import javax.inject.Inject

class MapperAllGenre @Inject constructor() {
    fun mapToUI(list: List<Genre>) =
        list.map {
            GenreUI(
                itemId = it._id,
                description = it.description.orEmpty(),
                name = it.name.orEmpty(),
                posters = it.posters.mapToPosters()
            )
        }

    fun List<String>.mapToPosters(): List<Any> {
        return if (this.size < 3) {
            return listOf(
                R.drawable.background_bisky_100,
                R.drawable.background_bisky_200,
                R.drawable.background_bisky_300
            )
        } else {
            this
        }
    }
}
