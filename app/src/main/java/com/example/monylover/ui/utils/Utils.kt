package com.example.monylover.ui.utils

import com.example.monylover.models.Recurrence
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

fun simplifyNumber(value: Float): String {
    return when {
        value >= 1000 && value < 1_000_000 -> DecimalFormat("0.#K").format(value / 1000)
        value >= 1_000_000 -> DecimalFormat("0.#M").format(value / 1_000_000)
        else -> DecimalFormat("0.#").format(value)
    }
}
fun LocalDate.formatDayForRange(): String {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)

    return when {
        this.year != today.year -> this.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
        else -> this.format(DateTimeFormatter.ofPattern("dd MMM"))
    }
}

data class DateRangeData(
    val start: LocalDate,
    val end: LocalDate,
    val daysInRange: Int
)
fun calculateDateRange(recurrence: Recurrence, page: Int): DateRangeData {
    val today = LocalDate.now()
    lateinit var start: LocalDate
    lateinit var end: LocalDate
    var daysInRange = 7

    when (recurrence) {
        Recurrence.Daily -> {
            start = LocalDate.now().minusDays(page.toLong())
            end = start
        }
        Recurrence.Weekly -> {
            start =
                LocalDate.now().minusDays(today.dayOfWeek.value.toLong() - 1)
                    .minusDays((page * 7).toLong())
            end = start.plusDays(6)
            daysInRange = 7
        }
        Recurrence.Monthly -> {
            start =
                LocalDate.of(today.year, today.month, 1)
                    .minusMonths(page.toLong())
            val numberOfDays =
                YearMonth.of(start.year, start.month).lengthOfMonth()
            end = start.plusDays(numberOfDays.toLong())
            daysInRange = numberOfDays
        }
        Recurrence.Yearly -> {
            start = LocalDate.of(today.year, 1, 1)
            end = LocalDate.of(today.year, 12, 31)
            daysInRange = 365
        }
        else -> Unit
    }

    return DateRangeData(
        start = start,
        end = end,
        daysInRange = daysInRange
    )
}