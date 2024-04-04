package com.example.bisky.ui.screen.searchscreen.filterscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bisky.R
import com.example.bisky.ui.screen.searchscreen.filterscreen.FilterView.Event
import com.example.bisky.ui.screen.searchscreen.filterscreen.items.DateFilter
import com.example.bisky.ui.screen.searchscreen.filterscreen.items.GenreFilter
import com.example.bisky.ui.screen.searchscreen.filterscreen.items.SortedFilter
import com.example.bisky.ui.screen.searchscreen.filterscreen.items.StatusFilter
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.SortAnimeFilter
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.StatusAnimeFilter

@Composable
fun FilterScreen(
    navController: NavController,
    viewModel: FilterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    FilterScreen(
        uiState,
        onDoneClick = {
            navController.popBackStack()
        },
        onSelectStatus = { status, isChecked ->
            viewModel.onEvent(Event.OnStatusSelected(status, isChecked))
        },
        onSelectSort = {
            viewModel.onEvent(Event.OnSortSelected(it))
        },
        onYearSelected = {
            viewModel.onEvent(Event.OnYearSelected(it))
        },
        onDialogDateShow = {
            viewModel.onEvent(Event.OnOpenDialogDate(it))
        },
        onSelectGenre = { id, isAdd ->
            viewModel.onEvent(Event.OnGenreSelected(id, isAdd))
        }
    )
}
@Composable
fun FilterScreen(
    uiState: FilterView.State,
    onDoneClick: () -> Unit,
    onSelectStatus: (StatusAnimeFilter, Boolean) -> Unit,
    onSelectSort: (SortAnimeFilter) -> Unit,
    onYearSelected: (Int) -> Unit,
    onDialogDateShow:(Boolean) -> Unit,
    onSelectGenre: (String, Boolean) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.bisky_dark_400)
            )
            .padding(top = 24.dp)
            .fillMaxSize()
    ) {
        item {
            StatusFilter(
                selectedStatus = uiState.selectedStatus,
                onSelectStatus,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
        item {
            SortedFilter(
                selectedSort = uiState.selectedSort,
                onSelectSort,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(start = 45.dp, end = 45.dp)
                .background(color = colorResource(id = R.color.bisky_dark_200))
            )
        }
        item {
            DateFilter(
                onYearSelected,
                onDialogDateShow,
                currentYear = uiState.currentYear,
                dialogVisible = uiState.isDateDialogShow,
                modifier = Modifier.padding(bottom = 12.dp, top = 12.dp)
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(start = 45.dp, end = 45.dp)
                .background(color = colorResource(id = R.color.bisky_dark_200))
            )
        }
        item {
            GenreFilter(
                listGenre = uiState.genreFilterUI,
                onGenreSelected = onSelectGenre,
                modifier = Modifier.padding(bottom = 12.dp, top = 12.dp)
            )
        }
        item {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp),
                onClick = {
                    onDoneClick()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.bisky_400)
                ),
                shape = RoundedCornerShape(4.dp, 4.dp, 4.dp, 4.dp)
            ) {
                Text(text = stringResource(id = R.string.btn_filter_done))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FilterScreenPreview() {
    FilterScreen(
        FilterView.State(),
        {},
        {it, sa ->},
        {},
        {},
        {},
        {it, sa ->}
    )
}