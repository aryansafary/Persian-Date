package com.arysapp.persiandate.ui.theme.datePickerTheme


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color

interface HorizontalDatePickerColors {
    @Composable
    fun backgroundColor(): State<Color>

    @Composable
    fun selectedTextColor(): State<Color>

    @Composable
    fun unSelectedBackgroundColor(): State<Color>

    @Composable
    fun unSelectedTextColor(): State<Color>

    @Composable
    fun selectedDayColor(): State<Color>

    @Composable
    fun todayBorderColor(): State<Color>

}
interface DialogDatePickerColors {
    @Composable fun dialogBackgroundColor(): State<Color>
    @Composable fun daySelectedBackgroundColor(): State<Color>
    @Composable fun daySelectedTextColor(): State<Color>
    @Composable fun dayUnselectedBackgroundColor(): State<Color>
    @Composable fun dayUnselectedTextColor(): State<Color>
    @Composable fun todayIndicatorColor(): State<Color>

    @Composable fun confirmButtonBackgroundColor(): State<Color>
    @Composable fun confirmButtonTextColor(): State<Color>
    @Composable fun dismissButtonBackgroundColor(): State<Color>
    @Composable fun dismissButtonTextColor(): State<Color>

    @Composable fun dropdownMenuItemBackgroundColor(): State<Color>
    @Composable fun dropdownMenuItemTextColor(): State<Color>
    @Composable fun dropdownMenuItemSelectedTextColor(): State<Color>
}

object DatePickerDefaults {

    @Composable
    fun horizontalDatePickerColors(
        backgroundColor: Color = MaterialTheme.colorScheme.background,
        selectedTextColor: Color = MaterialTheme.colorScheme.onPrimary,
        selectedDayColor: Color = MaterialTheme.colorScheme.primary,
        todayBorderColor: Color = MaterialTheme.colorScheme.error,
        unSelectedBackgroundColor: Color = MaterialTheme.colorScheme.surface,
        unSelectedTextColor: Color = MaterialTheme.colorScheme.onSurface
    ): HorizontalDatePickerColors = DefaultHorizontalDatePickerColors(
        backgroundColor,
        selectedTextColor,
        selectedDayColor,
        todayBorderColor,
        unSelectedBackgroundColor,
        unSelectedTextColor
    )

    @Composable
    fun jalaliDatePickerDialogColors(
        dialogBackgroundColor: Color = MaterialTheme.colorScheme.background,
        daySelectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
        daySelectedTextColor: Color = MaterialTheme.colorScheme.onPrimary,
        dayUnselectedBackgroundColor: Color = MaterialTheme.colorScheme.surface,
        dayUnselectedTextColor: Color = MaterialTheme.colorScheme.onSurface,
        todayIndicatorColor: Color = MaterialTheme.colorScheme.error,

        confirmButtonBackgroundColor: Color = MaterialTheme.colorScheme.primary,
        confirmButtonTextColor: Color = MaterialTheme.colorScheme.onPrimary,
        dismissButtonBackgroundColor: Color = MaterialTheme.colorScheme.secondary,
        dismissButtonTextColor: Color = MaterialTheme.colorScheme.onSecondary,

        dropdownMenuItemBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
        dropdownMenuItemTextColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
        dropdownMenuItemSelectedTextColor: Color = MaterialTheme.colorScheme.primary,
    ): DialogDatePickerColors = DefaultDialogDatePickerColors(
        dialogBackgroundColor,
        daySelectedBackgroundColor,
        daySelectedTextColor,
        dayUnselectedBackgroundColor,
        dayUnselectedTextColor,
        todayIndicatorColor,
        confirmButtonBackgroundColor,
        confirmButtonTextColor,
        dismissButtonBackgroundColor,
        dismissButtonTextColor,
        dropdownMenuItemBackgroundColor,
        dropdownMenuItemTextColor,
        dropdownMenuItemSelectedTextColor
    )
}
