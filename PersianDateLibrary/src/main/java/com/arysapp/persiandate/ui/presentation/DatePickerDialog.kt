package com.arysapp.persiandate.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arysapp.persiandate.ui.utils.JalaliCalendarUtils.adjustDateToValidRange
import com.arysapp.persiandate.ui.utils.JalaliCalendarUtils.getDaysInJalaliMonth
import com.arysapp.persiandate.core.JalaliDate
import com.arysapp.persiandate.ui.theme.datePickerTheme.DatePickerDefaults
import com.arysapp.persiandate.ui.theme.datePickerTheme.DialogDatePickerColors

@Composable
fun JalaliDatePickerDialog(
    initialDate: JalaliDate = JalaliDate.today(),
    minYear: Int = 1300,
    maxYear: Int = 1500,
    minDate: JalaliDate? = null,
    maxDate: JalaliDate? = null,
    onDismissRequest: () -> Unit,
    onDateSelected: (JalaliDate) -> Unit,
    confirmButtonText: String = "تایید",
    dismissButtonText: String = "انصراف",
    colors: DialogDatePickerColors = DatePickerDefaults.jalaliDatePickerDialogColors(
        dialogBackgroundColor = MaterialTheme.colorScheme.background,
        daySelectedBackgroundColor = MaterialTheme.colorScheme.primary,
        daySelectedTextColor = MaterialTheme.colorScheme.onPrimary,
        dayUnselectedBackgroundColor = MaterialTheme.colorScheme.surface,
        dayUnselectedTextColor = MaterialTheme.colorScheme.onSurface,
        todayIndicatorColor = MaterialTheme.colorScheme.outline,
        confirmButtonBackgroundColor = MaterialTheme.colorScheme.primary,
        confirmButtonTextColor = MaterialTheme.colorScheme.onPrimary,
        dismissButtonBackgroundColor = MaterialTheme.colorScheme.secondary,
        dismissButtonTextColor = MaterialTheme.colorScheme.onSecondary,
        dropdownMenuItemBackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        dropdownMenuItemTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        dropdownMenuItemSelectedTextColor = MaterialTheme.colorScheme.primary
    ),
    showTodayAtStart: Boolean = true
) {
    var selectedDate by remember { mutableStateOf(initialDate) }
    var expandedYear by remember { mutableStateOf(false) }
    var expandedMonth by remember { mutableStateOf(false) }

    val yearRange = (maxDate?.year ?: maxYear) downTo (minDate?.year ?: minYear)
    val allowedMonths = (1..12).filter { month ->
        val start = JalaliDate(selectedDate.year, month, 1)
        val end = JalaliDate(selectedDate.year, month, getDaysInJalaliMonth(selectedDate.year, month))
        val isAfterMin = minDate == null || end >= minDate
        val isBeforeMax = maxDate == null || start <= maxDate
        isAfterMin && isBeforeMax
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        containerColor = colors.dialogBackgroundColor().value, // rank: 1
        title = {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Year Dropdown - rank: 14
                Box {
                    Text(
                        text = "${selectedDate.year}",
                        modifier = Modifier
                            .clickable { expandedYear = true }
                            .padding(8.dp)
                    )
                    DropdownMenu(expanded = expandedYear, onDismissRequest = { expandedYear = false }) {
                        for (year in yearRange) {
                            DropdownMenuItem(
                                text = {
                                    Text("$year", color = colors.dropdownMenuItemTextColor().value) // rank: 12
                                },
                                modifier = Modifier.background(colors.dropdownMenuItemBackgroundColor().value), // rank: 11
                                onClick = {
                                    expandedYear = false
                                    adjustDateToValidRange(
                                        JalaliDate(year, selectedDate.month, 1),
                                        minDate, maxDate
                                    )?.let { selectedDate = it }
                                }
                            )
                        }
                    }
                }

                // Month Dropdown - rank: 15
                Box {
                    Text(
                        text = selectedDate.getMonthName(),
                        modifier = Modifier
                            .clickable { expandedMonth = true }
                            .padding(8.dp)
                    )
                    DropdownMenu(expanded = expandedMonth, onDismissRequest = { expandedMonth = false }) {
                        for (month in allowedMonths) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        JalaliDate.monthNames[month - 1],
                                        color = colors.dropdownMenuItemTextColor().value // rank: 12
                                    )
                                },
                                modifier = Modifier.background(colors.dropdownMenuItemBackgroundColor().value), // rank: 11
                                onClick = {
                                    expandedMonth = false
                                    adjustDateToValidRange(
                                        JalaliDate(selectedDate.year, month, 1),
                                        minDate, maxDate
                                    )?.let { selectedDate = it }
                                }
                            )
                        }
                    }
                }
            }
        },
        text = {
            DaysGrid(
                selectedDate = selectedDate,
                onDateClick = {
                    if ((minDate == null || it >= minDate) && (maxDate == null || it <= maxDate)) {
                        selectedDate = it
                    }
                },
                minDate = minDate,
                maxDate = maxDate,
                colors = colors,
                showTodayAtStart = showTodayAtStart
            )
        },
        confirmButton = {
            Button(
                onClick = { onDateSelected(selectedDate) },
                colors = ButtonDefaults.buttonColors(containerColor = colors.confirmButtonBackgroundColor().value) // rank: 7
            ) {
                Text(confirmButtonText, color = colors.confirmButtonTextColor().value) // rank: 8
            }
        },
        dismissButton = {
            Button(
                onClick = onDismissRequest,
                colors = ButtonDefaults.buttonColors(containerColor = colors.dismissButtonBackgroundColor().value) // rank: 9
            ) {
                Text(dismissButtonText, color = colors.dismissButtonTextColor().value) // rank: 10
            }
        }
    )
}

@Composable
fun DaysGrid(
    selectedDate: JalaliDate,
    onDateClick: (JalaliDate) -> Unit,
    minDate: JalaliDate?,
    maxDate: JalaliDate?,
    colors: DialogDatePickerColors,
    showTodayAtStart: Boolean
) {
    val startOfMonth = JalaliDate(selectedDate.year, selectedDate.month, 1)
    val startDayOfWeek = startOfMonth.getDayOfWeek()
    val daysInMonth = getDaysInJalaliMonth(selectedDate.year, selectedDate.month)
    val today = JalaliDate.today()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            JalaliDate.dayNames.forEach {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = colors.dayUnselectedTextColor().value
                )
            }
        }

        val totalCells = ((startDayOfWeek + daysInMonth + 6) / 7) * 7
        for (week in 0 until totalCells / 7) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                for (day in 0..6) {
                    val index = week * 7 + day
                    val dayNumber = index - startDayOfWeek + 1
                    if (dayNumber in 1..daysInMonth) {
                        val date = JalaliDate(selectedDate.year, selectedDate.month, dayNumber)
                        val isSelected = date == selectedDate
                        val isToday = date == today
                        val isDisabled = (minDate != null && date < minDate) || (maxDate != null && date > maxDate)

                        val backgroundColor = when {
                            isSelected -> colors.daySelectedBackgroundColor().value
                            else -> colors.dayUnselectedBackgroundColor().value
                        }

                        val textColor = when {
                            isSelected -> colors.daySelectedTextColor().value
                            else -> colors.dayUnselectedTextColor().value
                        }


                        Box(
                            modifier = Modifier
                                .border(
                                    width = if (isToday && showTodayAtStart) 2.dp else 0.dp,
                                    color = if (isToday && showTodayAtStart) colors.todayIndicatorColor().value else Color.Transparent,
                                    shape = CircleShape
                                )
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(backgroundColor)
                                .clickable(enabled = !isDisabled) {
                                    onDateClick(date)
                                } ,
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$dayNumber",
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.size(40.dp))
                    }
                }
            }
        }
    }
}









