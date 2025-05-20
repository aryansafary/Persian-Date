package com.arysapp.persiandate.core

import java.util.Calendar

data class JalaliDate(
    var year: Int = 0,
    var month: Int = 0,
    var day: Int = 0
) {
    companion object {
        internal val monthNames = arrayOf(
            "فروردین", "اردیبهشت", "خرداد",
            "تیر", "مرداد", "شهریور",
            "مهر", "آبان", "آذر",
            "دی", "بهمن", "اسفند"
        )

        internal val dayNames = arrayOf(
            "شنبه", "یکشنبه", "دوشنبه",
            "سه‌شنبه", "چهارشنبه", "پنجشنبه", "جمعه"
        )

        fun today(): JalaliDate {
            val calendar = Calendar.getInstance()
            return fromGregorian(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        }

        fun fromGregorian(gy: Int, gm: Int, gd: Int): JalaliDate {
            val g_d_m = intArrayOf(0, 31, if (isLeapGregorian(gy)) 29 else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
            var gy = gy
            var gm = gm
            var gd = gd

            var jy: Int
            var jm: Int
            var jd: Int

            val gy2 = gy - 1600
            val gm2 = gm - 1
            val gd2 = gd - 1

            var g_day_no = 365 * gy2 + (gy2 + 3) / 4 - (gy2 + 99) / 100 + (gy2 + 399) / 400
            for (i in 0 until gm2) g_day_no += g_d_m[i + 1]
            g_day_no += gd2

            var j_day_no = g_day_no - 79
            val j_np = j_day_no / 12053
            j_day_no %= 12053

            jy = 979 + 33 * j_np + 4 * (j_day_no / 1461)
            j_day_no %= 1461

            if (j_day_no >= 366) {
                jy += (j_day_no - 1) / 365
                j_day_no = (j_day_no - 1) % 365
            }

            val j_days_in_month = intArrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29)
            var i = 0
            while (i < 12 && j_day_no >= j_days_in_month[i]) {
                j_day_no -= j_days_in_month[i]
                i++
            }

            jm = i + 1
            jd = j_day_no + 1

            return JalaliDate(jy, jm, jd)
        }

        private fun isLeapGregorian(year: Int): Boolean {
            return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
        }
    }

    init {
        if (year == 0) {
            val today = today()
            this.year = today.year
            this.month = today.month
            this.day = today.day
        }
    }

    fun addDay(days: Int) {
        val calendar = Calendar.getInstance()
        val gDate = toGregorian()
        calendar.set(gDate[0], gDate[1] - 1, gDate[2])
        calendar.add(Calendar.DAY_OF_MONTH, days)
        val newDate = fromGregorian(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        this.year = newDate.year
        this.month = newDate.month
        this.day = newDate.day
    }

    fun getDayOfWeek(): Int {
        val calendar = Calendar.getInstance()
        val g = toGregorian()
        calendar.set(g[0], g[1] - 1, g[2])
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return when (dayOfWeek) {
            Calendar.SATURDAY -> 0
            Calendar.SUNDAY -> 1
            Calendar.MONDAY -> 2
            Calendar.TUESDAY -> 3
            Calendar.WEDNESDAY -> 4
            Calendar.THURSDAY -> 5
            Calendar.FRIDAY -> 6
            else -> 0
        }
    }


    fun getDayName(): String = dayNames[getDayOfWeek()]
    fun getMonthName(): String = monthNames[month - 1]

    fun toGregorian(): IntArray {
        val j_days_in_month = intArrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29)
        var jy = year - 979
        var jm = month - 1
        val jd = day - 1

        var j_day_no = 365 * jy + jy / 33 * 8 + (jy % 33 + 3) / 4
        for (i in 0 until jm) j_day_no += j_days_in_month[i]
        j_day_no += jd

        var g_day_no = j_day_no + 79

        var gy = 1600 + 400 * (g_day_no / 146097)
        g_day_no %= 146097

        var leap = true
        if (g_day_no >= 36525) {
            g_day_no--
            gy += 100 * (g_day_no / 36524)
            g_day_no %= 36524

            if (g_day_no >= 365) g_day_no++ else leap = false
        }

        gy += 4 * (g_day_no / 1461)
        g_day_no %= 1461

        if (g_day_no >= 366) {
            leap = false
            g_day_no--
            gy += g_day_no / 365
            g_day_no %= 365
        }

        val g_days_in_month = intArrayOf(31, if (leap) 29 else 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        var gm = 0
        while (g_day_no >= g_days_in_month[gm]) {
            g_day_no -= g_days_in_month[gm]
            gm++
        }
        val gd = g_day_no + 1

        return intArrayOf(gy, gm + 1, gd)
    }

    override fun toString(): String = "$year/${month.toString().padStart(2, '0')}/${day.toString().padStart(2, '0')}"




    fun getDaysInJalaliMonth(year: Int, month: Int): Int {
        return when (month) {
            in 1..6 -> 31
            in 7..11 -> 30
            12 -> if (isJalaliLeapYear(year)) 30 else 29
            else -> 0
        }
    }

    fun isJalaliLeapYear(year: Int): Boolean {
        val a = year - (year > 0).compareTo(false) * 474
        val b = a % 2820 + 474
        return ((b * 682) % 2816) < 682
    }



    operator fun compareTo(other: JalaliDate): Int {
        return when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> day - other.day
        }
    }

     fun greaterThan(other: JalaliDate): Boolean = this > other
     fun lessThan(other: JalaliDate): Boolean = this < other








}