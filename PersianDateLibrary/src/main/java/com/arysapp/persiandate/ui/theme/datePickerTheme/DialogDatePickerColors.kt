package com.arysapp.persiandate.ui.theme.datePickerTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

internal class DefaultDialogDatePickerColors(
    private val dialogBackgroundColor: Color,
    private val daySelectedBackgroundColor: Color,
    private val daySelectedTextColor: Color,
    private val dayUnselectedBackgroundColor: Color,
    private val dayUnselectedTextColor: Color,
    private val todayIndicatorColor: Color,

    private val confirmButtonBackgroundColor: Color,
    private val confirmButtonTextColor: Color,
    private val dismissButtonBackgroundColor: Color,
    private val dismissButtonTextColor: Color,

    private val dropdownMenuItemBackgroundColor: Color,
    private val dropdownMenuItemTextColor: Color,
    private val dropdownMenuItemSelectedTextColor: Color,
) : DialogDatePickerColors {

    @Composable override fun dialogBackgroundColor(): State<Color> =
        rememberUpdatedState(dialogBackgroundColor)

    @Composable override fun daySelectedBackgroundColor(): State<Color> =
        rememberUpdatedState(daySelectedBackgroundColor)

    @Composable override fun daySelectedTextColor(): State<Color> =
        rememberUpdatedState(daySelectedTextColor)

    @Composable override fun dayUnselectedBackgroundColor(): State<Color> =
        rememberUpdatedState(dayUnselectedBackgroundColor)

    @Composable override fun dayUnselectedTextColor(): State<Color> =
        rememberUpdatedState(dayUnselectedTextColor)

    @Composable override fun todayIndicatorColor(): State<Color> =
        rememberUpdatedState(todayIndicatorColor)

    @Composable override fun confirmButtonBackgroundColor(): State<Color> =
        rememberUpdatedState(confirmButtonBackgroundColor)

    @Composable override fun confirmButtonTextColor(): State<Color> =
        rememberUpdatedState(confirmButtonTextColor)

    @Composable override fun dismissButtonBackgroundColor(): State<Color> =
        rememberUpdatedState(dismissButtonBackgroundColor)

    @Composable override fun dismissButtonTextColor(): State<Color> =
        rememberUpdatedState(dismissButtonTextColor)

    @Composable override fun dropdownMenuItemBackgroundColor(): State<Color> =
        rememberUpdatedState(dropdownMenuItemBackgroundColor)

    @Composable override fun dropdownMenuItemTextColor(): State<Color> =
        rememberUpdatedState(dropdownMenuItemTextColor)

    @Composable override fun dropdownMenuItemSelectedTextColor(): State<Color> =
        rememberUpdatedState(dropdownMenuItemSelectedTextColor)
}
