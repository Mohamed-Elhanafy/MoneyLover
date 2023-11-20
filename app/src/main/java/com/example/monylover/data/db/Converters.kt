package com.example.monylover.data.db

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.TypeConverter
import androidx.room.TypeConverters
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
        return gson.toJson(recurrence)
    }

    @TypeConverter
    fun fromGsonToRecurrence(recurrence: String): Recurrence {
        return gson.fromJson(recurrence, Recurrence::class.java)
    }


    @TypeConverter
    fun fromLocalDateTimeToGson(localDateTime: LocalDateTime): String {
        return Gson().toJson(localDateTime)
    }

    @TypeConverter
    fun fromGsonToLocalDateTime(localDateTime: String): LocalDateTime {
        return Gson().fromJson(localDateTime, LocalDateTime::class.java)
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