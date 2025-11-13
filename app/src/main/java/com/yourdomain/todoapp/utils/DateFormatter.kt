package com.yourdomain.todoapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val dateTimeFormat = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())

    fun formatDate(date: Date): String = dateFormat.format(date)

    fun formatTime(date: Date): String = timeFormat.format(date)

    fun formatDateTime(date: Date): String = dateTimeFormat.format(date)

    fun parseDate(dateString: String): Date? = dateFormat.parse(dateString)

    fun parseDateTime(dateTimeString: String): Date? = dateTimeFormat.parse(dateTimeString)
}
