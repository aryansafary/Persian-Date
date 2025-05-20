package com.arysapp.persiandate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.arysapp.persiandate.ui.theme.datePickerTheme.DatePickerDefaults
import com.arysapp.persiandate.ui.theme.datePickerTheme.DialogDatePickerColors
import com.arysapp.persiandate.ui.theme.datePickerTheme.HorizontalDatePickerColors


// Purples
val Purple40 = Color(0xFF7E57C2)
val Purple80 = Color(0xFFB39DDB)
val PurpleGrey40 = Color(0xFF5E5C6C)
val PurpleGrey80 = Color(0xFFD0C9E3)

// Teals
val Teal40 = Color(0xFF03DAC6)
val Teal80 = Color(0xFF70EFDE)

// Reds
val RedError = Color(0xFFB00020)

// Light Theme Colors
val LightPrimaryContainer = Color(0xFFEDE7F6)
val LightOnPrimaryContainer = Color(0xFF1E1B29)
val LightSecondaryContainer = Color(0xFFE0F2F1)
val LightOnSecondaryContainer = Color(0xFF004D40)
val LightBackground = Color(0xFFFDFBFF)
val LightOnBackground = Color(0xFF1C1B1F)
val LightSurface = Color(0xFFFFFFFF)
val LightOnSurface = Color(0xFF1C1B1F)
val LightSurfaceVariant = Color(0xFFE7E0EC)
val LightOnSurfaceVariant = Color(0xFF49454F)
val LightOutline = Color(0xFF79747E)

// Dark Theme Colors
val DarkPrimaryContainer = Color(0xFF4A0072)
val DarkOnPrimaryContainer = Color(0xFFE1BEE7)
val DarkSecondaryContainer = Color(0xFF004D40)
val DarkOnSecondaryContainer = Color(0xFFB2DFDB)
val DarkBackground = Color(0xFF121212)
val DarkOnBackground = Color(0xFFE6E1E5)
val DarkSurface = Color(0xFF1E1E1E)
val DarkOnSurface = Color(0xFFE6E1E5)
val DarkSurfaceVariant = Color(0xFF49454F)
val DarkOnSurfaceVariant = Color(0xFFCAC4D0)
val DarkOutline = Color(0xFF938F99)






// Light Theme Colors
val LightDialogBackground = Color(0xFFFDFDFD)
val LightDaySelectedBackground = Color(0xFF1E88E5)
val LightDaySelectedText = Color.White
val LightDayUnselectedBackground = Color(0xFFE3F2FD)
val LightDayUnselectedText = Color(0xFF0D47A1)
val LightTodayIndicator = Color(0xFF42A5F5)
val LightConfirmButtonBackground = Color(0xFF43A047)
val LightConfirmButtonText = Color.White
val LightDismissButtonBackground = Color(0xFFE53935)
val LightDismissButtonText = Color.White
val LightDropdownMenuItemBackground = Color(0xFFF1F1F1)
val LightDropdownMenuItemText = Color(0xFF212121)
val LightDropdownMenuItemSelectedText = Color(0xFF1E88E5)

// Dark Theme Colors
val DarkDialogBackground = Color(0xFF121212)
val DarkDaySelectedBackground = Color(0xFF90CAF9)
val DarkDaySelectedText = Color.Black
val DarkDayUnselectedBackground = Color(0xFF1E1E1E)
val DarkDayUnselectedText = Color(0xFFBBDEFB)
val DarkTodayIndicator = Color(0xFF64B5F6)
val DarkConfirmButtonBackground = Color(0xFF66BB6A)
val DarkConfirmButtonText = Color.Black
val DarkDismissButtonBackground = Color(0xFFEF5350)
val DarkDismissButtonText = Color.Black
val DarkDropdownMenuItemBackground = Color(0xFF2C2C2C)
val DarkDropdownMenuItemText = Color(0xFFEEEEEE)
val DarkDropdownMenuItemSelectedText = Color(0xFF90CAF9)


// Light Theme Colors
val LightHorizontalBackground = Color(0xFFFFFFFF)
val LightHorizontalSelectedText = Color(0xFFFFFFFF)
val LightHorizontalSelectedDay = Color(0xFF1E88E5)
val LightHorizontalTodayBorder = Color(0xFF1E88E5)
val LightHorizontalUnselectedBackground = Color(0xFFF0F0F0)
val LightHorizontalUnselectedText = Color(0xFF555555)

// Dark Theme Colors
val DarkHorizontalBackground = Color(0xFF121212)
val DarkHorizontalSelectedText = Color(0xFF000000)
val DarkHorizontalSelectedDay = Color(0xFF90CAF9)
val DarkHorizontalTodayBorder = Color(0xFF90CAF9)
val DarkHorizontalUnselectedBackground = Color(0xFF2C2C2C)
val DarkHorizontalUnselectedText = Color(0xFFCCCCCC)





// File: DatePickerColor.kt (یا Color.kt)

@Composable
fun rememberHorizontalDatePickerColors(): HorizontalDatePickerColors {
    return if (isSystemInDarkTheme()) {
        DatePickerDefaults.horizontalDatePickerColors(
            backgroundColor = DarkHorizontalBackground,
            selectedTextColor = DarkHorizontalSelectedText,
            selectedDayColor = DarkHorizontalSelectedDay,
            todayBorderColor = DarkHorizontalTodayBorder,
            unSelectedBackgroundColor = DarkHorizontalUnselectedBackground,
            unSelectedTextColor = DarkHorizontalUnselectedText
        )
    } else {
        DatePickerDefaults.horizontalDatePickerColors(
            backgroundColor = LightHorizontalBackground,
            selectedTextColor = LightHorizontalSelectedText,
            selectedDayColor = LightHorizontalSelectedDay,
            todayBorderColor = LightHorizontalTodayBorder,
            unSelectedBackgroundColor = LightHorizontalUnselectedBackground,
            unSelectedTextColor = LightHorizontalUnselectedText
        )
    }
}





@Composable
fun rememberDatePickerColors(): DialogDatePickerColors {
    return if (isSystemInDarkTheme()) {
        DatePickerDefaults.jalaliDatePickerDialogColors(
            dialogBackgroundColor = DarkDialogBackground,
            daySelectedBackgroundColor = DarkDaySelectedBackground,
            daySelectedTextColor = DarkDaySelectedText,
            dayUnselectedBackgroundColor = DarkDayUnselectedBackground,
            dayUnselectedTextColor = DarkDayUnselectedText,
            todayIndicatorColor = DarkTodayIndicator,
            confirmButtonBackgroundColor = DarkConfirmButtonBackground,
            confirmButtonTextColor = DarkConfirmButtonText,
            dismissButtonBackgroundColor = DarkDismissButtonBackground,
            dismissButtonTextColor = DarkDismissButtonText,
            dropdownMenuItemBackgroundColor = DarkDropdownMenuItemBackground,
            dropdownMenuItemTextColor = DarkDropdownMenuItemText,
            dropdownMenuItemSelectedTextColor = DarkDropdownMenuItemSelectedText
        )
    } else {
        DatePickerDefaults.jalaliDatePickerDialogColors(
            dialogBackgroundColor = LightDialogBackground,
            daySelectedBackgroundColor = LightDaySelectedBackground,
            daySelectedTextColor = LightDaySelectedText,
            dayUnselectedBackgroundColor = LightDayUnselectedBackground,
            dayUnselectedTextColor = LightDayUnselectedText,
            todayIndicatorColor = LightTodayIndicator,
            confirmButtonBackgroundColor = LightConfirmButtonBackground,
            confirmButtonTextColor = LightConfirmButtonText,
            dismissButtonBackgroundColor = LightDismissButtonBackground,
            dismissButtonTextColor = LightDismissButtonText,
            dropdownMenuItemBackgroundColor = LightDropdownMenuItemBackground,
            dropdownMenuItemTextColor = LightDropdownMenuItemText,
            dropdownMenuItemSelectedTextColor = LightDropdownMenuItemSelectedText
        )
    }
}


