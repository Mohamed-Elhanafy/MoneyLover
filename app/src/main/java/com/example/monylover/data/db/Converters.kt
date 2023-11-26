package com.example.monylover.data.db

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.TypeConverter
import com.example.monylover.models.Category
import com.example.monylover.models.Recurrence
import com.example.monylover.models.RecurrenceTypeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.time.LocalDateTime


class Converters {
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(Recurrence::class.java, RecurrenceTypeAdapter())
        .create()

    @TypeConverter
    fun fromRecurrenceToGson(recurrence: Recurrence): String {

        when (recurrence) {
            is Recurrence.None -> return Gson().toJson(Recurrence.None)
            is Recurrence.Daily -> return Gson().toJson(Recurrence.Daily)
            is Recurrence.Weekly -> return Gson().toJson(Recurrence.Weekly)
            is Recurrence.Monthly -> return Gson().toJson(Recurrence.Monthly)
            is Recurrence.Yearly -> return Gson().toJson(Recurrence.Yearly)
        }

    }

    @TypeConverter
    fun fromGsonToRecurrence(recurrence: String): Recurrence {

        when(recurrence){
            "None" -> return Gson().fromJson(recurrence, Recurrence.None::class.java)
            "Daily" -> return Gson().fromJson(recurrence, Recurrence.Daily::class.java)
            "Weekly" -> return Gson().fromJson(recurrence, Recurrence.Weekly::class.java)
            "Monthly" -> return Gson().fromJson(recurrence, Recurrence.Monthly::class.java)
            "Yearly" -> return Gson().fromJson(recurrence, Recurrence.Yearly::class.java)
            else -> return Gson().fromJson(recurrence, Recurrence.None::class.java)
        }

    }

/*
    @TypeConverter
    fun fromLocalDateTimeToGson(localDateTime: LocalDateTime): String {
        return Gson().toJson(localDateTime)
    }

    @TypeConverter
    fun fromGsonToLocalDateTime(localDateTime: String): LocalDateTime {
        return Gson().fromJson(localDateTime, LocalDateTime::class.java)
    }*/

    @TypeConverter
    fun toDate(dateString: String?): LocalDateTime? {
        return if (dateString == null) {
            null
        } else {
            LocalDateTime.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime?): String? {
        return date?.toString()
    }
    @TypeConverter
    fun fromCategoryToGson(category: Category): String {
        return Gson().toJson(category)
    }

    @TypeConverter
    fun fromGsonToCategory(category: String): Category {
        return Gson().fromJson(category, Category::class.java)
    }

    @TypeConverter
    fun fromColorToGson(color: Color): Int {
        return color.toArgb()
    }

    @TypeConverter
    fun fromGsonToColor(color: Int): Color {
        return Color(color)
    }


}