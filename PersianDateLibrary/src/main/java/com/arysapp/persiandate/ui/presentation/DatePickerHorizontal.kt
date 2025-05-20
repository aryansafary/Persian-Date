package com.arysapp.persiandate.ui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arysapp.persiandate.core.JalaliDate
import com.arysapp.persiandate.ui.theme.datePickerTheme.HorizontalDatePickerColors
import com.arysapp.persiandate.ui.theme.datePickerTheme.DatePickerDefaults



@Composable
fun HorizontalDatePicker(
    modifier: Modifier = Modifier,
    daysCount: Int = 7,
    selectedDate: JalaliDate,
    onDateSelected: (JalaliDate) -> Unit,
    shape: Shape = RoundedCornerShape(16.dp),
    colors: HorizontalDatePickerColors =
        DatePickerDefaults.horizontalDatePickerColors(
            backgroundColor = MaterialTheme.colorScheme.background,
            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
            selectedDayColor = MaterialTheme.colorScheme.primary,
    todayBorderColor = MaterialTheme.colorScheme.outline,
    unSelectedBackgroundColor = MaterialTheme.colorScheme.surface,
    unSelectedTextColor = MaterialTheme.colorScheme.onSurface
        ),
    fontSize: TextUnit = 14.sp,
    showTodayAtStart: Boolean = true
) {
    val startDate = if (showTodayAtStart) JalaliDate.Companion.today() else selectedDate
    val dates = remember {
        List(daysCount) { offset ->
            JalaliDate(startDate.year, startDate.month, startDate.day).apply { addDay(offset) }
        }
    }
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.backgroundColor().value)
        ,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)

    ) {
        items(dates) { date ->
            val isSelected = date == selectedDate
            val isToday = date == JalaliDate.today()

            Card(
                modifier = Modifier
                    .clickable { onDateSelected(date) }
                    .padding(8.dp)
                    .width(72.dp)
                    .height(100.dp),
                shape = shape,
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) {
                        colors.selectedDayColor().value
                    } else {
                        colors.unSelectedBackgroundColor().value
                    }
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (isSelected) 6.dp else 2.dp
                ),
            border =
                if (isToday&&showTodayAtStart) {
                    BorderStroke(2.dp, colors.todayBorderColor().value)
                } else {
                    null
                }


            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = date.getDayName(),
                        color = if (isSelected) colors.selectedTextColor().value else colors.unSelectedTextColor().value,
                        fontSize = fontSize,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = date.day.toString(),
                        color = if (isSelected) colors.selectedTextColor().value else colors.unSelectedTextColor().value,
                        fontSize = (fontSize.value + 4).sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = date.getMonthName(),
                        color = if (isSelected) colors.selectedTextColor().value else colors.unSelectedTextColor().value,
                        fontSize = fontSize,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}



