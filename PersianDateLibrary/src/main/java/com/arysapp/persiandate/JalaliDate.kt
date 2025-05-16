package ir.vaghtban.ui.utils

import java.util.Calendar

// کلاس تاریخ شمسی با قابلیت محاسبات پایه
class JalaliDate(
    private var year: Int = 0,
    private var month: Int = 0,
    internal var day: Int = 0
) {
    companion object {
        private val monthNames = arrayOf(
            "فروردین", "اردیبهشت", "خرداد",
            "تیر", "مرداد", "شهریور",
            "مهر", "آبان", "آذر",
            "دی", "بهمن", "اسفند"
        )

        private val dayNames = arrayOf(
            "یکشنبه", "دوشنبه", "سه‌شنبه",
            "چهارشنبه", "پنجشنبه", "جمعه", "شنبه"
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
            val jd = gregorianToJulian(gy, gm, gd)
            return julianToJalali(jd)
        }

        private fun gregorianToJulian(gy: Int, gm: Int, gd: Int): Int {
            return (1461 * (gy + 4800 + (gm - 14) / 12) / 4
                    + 367 * (gm - 2 - 12 * ((gm - 14) / 12)) / 12
                    - 3 * ((gy + 4900 + (gm - 14) / 12) / 100) / 4
                    + gd - 32075)
        }

        private fun julianToJalali(jd: Int): JalaliDate {
            var jd = jd
            jd += 10613804

            val gy = (jd * 4 - 1) / 1461
            val jy = 31 * gy / 128
            jd = (jd - 59) - 365 * gy - gy / 4

            var jm = (jd * 4 - 1) / 120
            if (jm > 11) jm = 11

            // اصلاح فرمول محاسبه روز
            val jd2 = jd - 30 * jm - (jm * 3 + 2) / 5 + 1 // اضافه شدن +1

            return JalaliDate(jy + 621, jm + 1, jd2)
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
        var jd = toJulianDay() + days
        val newDate = julianToJalali(jd)
        year = newDate.year
        month = newDate.month
        day = newDate.day
    }

    private fun toJulianDay(): Int {
        return (year - 621) * 365 +
                ((year - 621) / 4).toInt() + // تبدیل به Int برای جلوگیری از خطا
                month * 31 -
                (month * 6) / 10 +
                day +
                10613804
    }


    fun getDayOfWeek(): Int = (toJulianDay() + 3) % 7

    fun getDayName() = dayNames[getDayOfWeek()]
    fun getMonthName() = monthNames[month - 1]
    override fun equals(other: Any?): Boolean {
        return other is JalaliDate &&
                year == other.year &&
                month == other.month &&
                day == other.day
    }

    override fun hashCode(): Int = year * 10000 + month * 100 + day
    override fun toString() = "$year/${month.toString().padStart(2, '0')}/${day.toString().padStart(2, '0')}"
}
