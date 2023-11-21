package com.example.monylover.models

import androidx.room.Entity


@Entity(tableName = "recurrence")
sealed class Recurrence(val name: String, val target: String) {
        object None : Recurrence("None", "None")
        object Daily : Recurrence("Daily", "Today")
        object Weekly : Recurrence("Weekly", "This week")
        object Monthly : Recurrence("Monthly", "This month")
        object Yearly : Recurrence("Yearly", "This year")
}