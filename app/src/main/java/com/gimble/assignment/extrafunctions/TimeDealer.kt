package com.gimble.assignment.extrafunctions

import com.gimble.assignment.data.remote.dashboardapi.OverallUrlChart
import com.github.mikephil.charting.data.Entry
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.reflect.full.declaredMemberProperties
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.reflect.full.memberProperties

class TimeDealer {

    private fun getDataForDate(date: String): Int {
        // Assume you have a method to get data for a specific date
        // This is a placeholder, replace it with your actual implementation
        return 0
    }




    fun getLast8Days(): List<String> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = Date()
        val calendar = Calendar.getInstance()

        val last8Days = mutableListOf<String>()

        // Include the current date
        last8Days.add(dateFormat.format(currentDate))

        // Iterate for the next 7 days
        for (i in 1 until 8) {
            calendar.time = currentDate
            calendar.add(Calendar.DAY_OF_YEAR, -i)
            last8Days.add(dateFormat.format(calendar.time))
        }

        return last8Days
    }

    fun strtodate(date: String): LocalDate? {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return try {
            LocalDate.parse(date, formatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }

}

