package dev.olaore.greyandroid.util

import java.text.SimpleDateFormat
import java.util.*

const val ONE_DAY = 86400000

const val day = "day"
const val month = "month"
const val year = "year"

fun String.getTimeDifference(): String {
    val today = Date()
    val stringDate = this.parseToDate()

    val time = today.time - (stringDate?.time ?: 0)

    val amountOfDays = (time / ONE_DAY).toInt()
    val amountOfMonths = amountOfDays / 31
    val amountOfYears = amountOfDays / 365

    var selectedAmount: Int

    var result = when {
        amountOfDays < 31 -> {
            selectedAmount = amountOfDays
            "$amountOfDays $day"
        }
        amountOfMonths < 12 -> {
            selectedAmount = amountOfMonths
            "$amountOfMonths $month"
        }
        else -> {
            selectedAmount = amountOfYears
            "$amountOfYears $year"
        }
    }

    result += if (selectedAmount > 1) "s" else ""

    return result
}

fun String.parseToDate(
    format: String = "yyyy-MM-dd"
): Date? = run {
    SimpleDateFormat(format, Locale.UK).parse(this)
}
