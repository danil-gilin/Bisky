package com.example.bisky.ui.screen.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bisky.data.seasonanime.SeasonAnimeRepository
import com.example.domain.repository.seasonanime.model.RequestSeasonAnimeParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Month
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val seasonAnimeRepository: SeasonAnimeRepository
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenView.State())
    val uiState: StateFlow<HomeScreenView.State> = _uiState


    init {
        viewModelScope.launch {
            getSeasonAnime()
        }
    }

    private suspend fun getSeasonAnime(){
        val result = seasonAnimeRepository.getSeasonAnime(getRequestSeasonAnimeParams())
        result
    }

    private fun getRequestSeasonAnimeParams(): RequestSeasonAnimeParams {
        val currentYear = LocalDate.now().year
        val currentMonth = LocalDate.now().month

        val (startMonth, endMonth) = when (currentMonth) {
            Month.DECEMBER, Month.JANUARY, Month.FEBRUARY -> Pair(Month.DECEMBER, Month.FEBRUARY)
            Month.MARCH, Month.APRIL, Month.MAY -> Pair(Month.MARCH, Month.MAY)
            Month.JUNE, Month.JULY, Month.AUGUST -> Pair(Month.JUNE, Month.AUGUST)
            Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER -> Pair(Month.SEPTEMBER, Month.NOVEMBER)
            else -> Pair(Month.DECEMBER, Month.FEBRUARY)
        }

        val startDate = LocalDate.of(currentYear - if (startMonth == Month.DECEMBER) 1 else 0, startMonth, 1)
        val endDate = LocalDate.of(currentYear, endMonth, endMonth.maxLength())

        val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val startDateString = startDate.format(formatter)
        val endDateString = endDate.format(formatter)
        return RequestSeasonAnimeParams(startDateString, endDateString)
    }
}