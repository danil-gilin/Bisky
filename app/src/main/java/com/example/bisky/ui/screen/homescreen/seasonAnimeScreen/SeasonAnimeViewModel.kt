package com.example.bisky.ui.screen.homescreen.seasonAnimeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.R
import com.example.bisky.data.seasonanime.SeasonAnimeRepository
import com.example.bisky.domain.repository.seasonanime.model.RequestSeasonAnimeParams
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.SeasonAnimeScreenView.Event
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.mapper.SeasonAnimeMapper
import com.example.bisky.ui.screen.homescreen.seasonAnimeScreen.model.Season
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.Month
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SeasonAnimeViewModel @Inject constructor(
    private val seasonAnimeRepository: SeasonAnimeRepository,
    private val seasonAnimeMapper: SeasonAnimeMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow(SeasonAnimeScreenView.State())
    val uiState: StateFlow<SeasonAnimeScreenView.State> = _uiState

    init {
        viewModelScope.launch {
            updateTitle()
            getSeasonAnime()
        }
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnScrollItem -> _uiState.update { it.copy(positionScroll = event.position) }
        }
    }

    private fun updateTitle() {
        val currentSeason = getSeason()
        val (title, img) = when (currentSeason) {
            Season.Winter -> R.string.anime_winter_title to R.drawable.anime_winter
            Season.Summer -> R.string.anime_summer_title to R.drawable.anime_summer
            Season.Autumn -> R.string.anime_autumn_title to R.drawable.anime_autumn
            Season.Spring -> R.string.anime_spring_title to R.drawable.anime_spring
        }
        _uiState.update {
            it.copy(seasonImg = img, seasonTitle = title)
        }
    }

    private suspend fun getSeasonAnime() {
        val result = seasonAnimeRepository.getSeasonAnime(getRequestSeasonAnimeParams())
        val items = seasonAnimeMapper.map(result)
        _uiState.update {
            it.copy(itemsAnime = items)
        }
    }

    private fun getRequestSeasonAnimeParams(): RequestSeasonAnimeParams {
        val currentYear = LocalDate.now().year
        val currentMonth = LocalDate.now().month

        val (startMonth, endMonth) = when (currentMonth) {
            Month.DECEMBER, Month.JANUARY, Month.FEBRUARY -> Month.DECEMBER to Month.FEBRUARY
            Month.MARCH, Month.APRIL, Month.MAY -> Month.MARCH to Month.MAY
            Month.JUNE, Month.JULY, Month.AUGUST -> Month.JUNE to Month.AUGUST
            Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER -> Month.SEPTEMBER to Month.NOVEMBER
            else -> Month.DECEMBER to Month.FEBRUARY
        }

        val startDate =
            LocalDate.of(currentYear - if (startMonth == Month.DECEMBER) 1 else 0, startMonth, 1)
        val endDate = LocalDate.of(currentYear, endMonth, endMonth.maxLength())

        val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val startDateString = startDate.format(formatter)
        val endDateString = endDate.format(formatter)
        return RequestSeasonAnimeParams(startDateString, endDateString)
    }

    private fun getSeason(): Season {
        return when (LocalDate.now().month) {
            Month.DECEMBER, Month.JANUARY, Month.FEBRUARY -> Season.Winter
            Month.MARCH, Month.APRIL, Month.MAY -> Season.Spring
            Month.JUNE, Month.JULY, Month.AUGUST -> Season.Summer
            Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER -> Season.Autumn
            else -> Season.Winter
        }
    }
}
