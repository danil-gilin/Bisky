package com.example.bisky.ui.screen.homescreen.newseriesscreen.mapper

import android.content.Context
import com.example.bisky.domain.repository.newseries.model.NewSeriesAnime
import com.example.bisky.ui.screen.homescreen.newseriesscreen.model.NewSeriesAnimeUI
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NewSeriesMapper @Inject constructor(
    @ApplicationContext private val context: Context
) {


    fun mapToUi(items: List<NewSeriesAnime>) = items.mapNotNull {
        if (it.series == null) {
            null
        } else {
            NewSeriesAnimeUI(
                itemId = it.id,
                name = it.name,
                poster = it.poster,
                infoSeries = "${it.series} серия"
            )
        }
    }
}