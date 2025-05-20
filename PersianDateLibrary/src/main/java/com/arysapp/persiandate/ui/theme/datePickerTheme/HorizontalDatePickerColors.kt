package com.arysapp.persiandate.ui.theme.datePickerTheme


import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

internal class DefaultHorizontalDatePickerColors(
    private val backgroundColor: Color,
    private val selectedTextColor: Color,
    private val selectedDayColor: Color,
    private val todayBorderColor: Color,
    private val unSelectedBackgroundColor: Color,
    private val unSelectedTextColor: Color,
) : HorizontalDatePickerColors {

    @Composable override fun backgroundColor(): State<Color> =
        rememberUpdatedState(backgroundColor)

    @Composable override fun selectedTextColor(): State<Color> =
        rememberUpdatedState(selectedTextColor)

    @Composable override fun selectedDayColor(): State<Color> =
        rememberUpdatedState(selectedDayColor)

    @Composable override fun todayBorderColor(): State<Color> =
        rememberUpdatedState(todayBorderColor)

    @Composable override fun unSelectedBackgroundColor(): State<Color> =
        rememberUpdatedState(unSelectedBackgroundColor)

    @Composable override fun unSelectedTextColor(): State<Color> =
        rememberUpdatedState(unSelectedTextColor)
}
