package com.arysapp.persiandate.ui.utils

import com.arysapp.persiandate.core.JalaliDate

object JalaliCalendarUtils {


    fun adjustDateToValidRange(date: JalaliDate, minDate: JalaliDate?, maxDate: JalaliDate?): JalaliDate? {
        val daysInMonth = getDaysInJalaliMonth(date.year, date.month)
        for (day in 1..daysInMonth) {
            val candidate = date.copy(day = day)
            if ((minDate == null || candidate >= minDate) && (maxDate == null || candidate <= maxDate)) {
                return candidate
            }
        }
        return null
    }


    fun getDaysInJalaliMonth(year: Int, month: Int): Int = when (month) {
        in 1..6 -> 31
        in 7..11 -> 30
        else -> if (isLeapJalali(year)) 30 else 29
    }

    fun isLeapJalali(year: Int): Boolean {
        val a = (year - (year > 0).compareTo(false)) % 33
        return a in listOf(1, 5, 9, 13, 17, 22, 26, 30)
    }

}
