@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.bisky.ui.screen.searchscreen.filterscreen.items

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bisky.R
import com.example.bisky.ui.elements.noRippleClickable
import com.example.bisky.ui.screen.searchscreen.filterscreen.model.SortAnimeFilter
import java.time.LocalDate

val LIST_YEAR = (1960..LocalDate.now().year).toList().reversed()

@Composable
fun DateFilter(
    onYearSelected: (Int) -> Unit,
    onDialogShow:(Boolean) -> Unit,
    currentYear: Int,
    dialogVisible:Boolean,
    modifier: Modifier = Modifier
) {
    if (dialogVisible) {
        YearPickerDialog(
            onYearSelected,
            onDialogShow,
            currentYear
        )
    }
    Row(
        modifier = modifier
            .background(colorResource(id = R.color.bisky_dark_400))
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.date_filter),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(16.dp, 0.dp, 4.dp, 0.dp)
                .align(Alignment.CenterVertically)
                .weight(1.0f)
                .fillMaxWidth(),
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_100)
        )
        Text(
            text = currentYear.toString(),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(end = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(colorResource(id = R.color.bisky_dark_200))
                .align(Alignment.CenterVertically)
                .padding(8.dp, 4.dp, 8.dp, 4.dp)
                .noRippleClickable {
                    onDialogShow(true)
                },
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.02).sp,
            color = colorResource(R.color.light_100)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YearPickerDialog(
    onYearSelected: (Int) -> Unit,
    onDialogShow:(Boolean) -> Unit,
    currentYear: Int
) {
    val index = if (LIST_YEAR.indexOf(currentYear) > 2)
        LIST_YEAR.indexOf(currentYear) - 2
    else
        LIST_YEAR.indexOf(currentYear)
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = index)
    ModalBottomSheet(
        containerColor = colorResource(id = R.color.bisky_dark_200),
        onDismissRequest = {
            onDialogShow(false)
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(colorResource(id = R.color.bisky_dark_200))
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(colorResource(id = R.color.bisky_dark_200))
                ) {
                    items(LIST_YEAR) { year ->
                        Card(
                            modifier = Modifier
                                .clickable {
                                    onYearSelected(year)
                                    onDialogShow(false)
                                }
                                .fillMaxWidth()
                                .padding(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (year == currentYear)
                                    colorResource(id = R.color.bisky_dark_100)
                                else colorResource(id = R.color.light_300)
                            ),
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                fontSize = 22.sp,
                                text = "$year",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun DateFilterPreview() {
    DateFilter(
        onDialogShow = {},
        onYearSelected = {},
        dialogVisible = false,
        currentYear = 2002
    )
}