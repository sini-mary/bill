package megan.wamboi.billsapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

class DateTimeUtils {
    companion object {

        @RequiresApi(Build.VERSION_CODES.O)
        fun getFirstDayOfWeek(): String {
            val now = LocalDateTime.now()
            val first = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            return formatDateDDMMyy(first)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getLastDayOfWeek(): String {
            val now = LocalDateTime.now()
            val last = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
            return formatDateDDMMyy(last)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun formatDateDDMMyy(localDate: LocalDateTime): String {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            return localDate.format(formatter)
        }
        fun formatSingleDigitDate(dateStr:String):String{

        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getDayOfWeek(day: String): DayOfWeek? {
            val daysMap = mapOf(
                "1" to DayOfWeek.MONDAY,
                "2" to DayOfWeek.TUESDAY,
                "3" to DayOfWeek.WEDNESDAY,
                "4" to DayOfWeek.THURSDAY,
                "5" to DayOfWeek.FRIDAY,
                "6" to DayOfWeek.SATURDAY,
                "7" to DayOfWeek.SUNDAY
            )

            return daysMap[day]
        }
        @RequiresApi(Build.VERSION_CODES.O)
        fun getCurrentYear():String{
            val now = LocalDateTime.now()
            return now.year.toString()
        }
    }
}
