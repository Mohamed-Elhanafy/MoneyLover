package com.example.monylover.data.db

import androidx.room.TypeConverter
import com.example.monylover.models.Category
import com.example.monylover.models.Recurrence
import com.google.gson.Gson
import java.time.LocalDateTime


class Converters {
    @TypeConverter
    fun fromRecurrenceToGson(recurrence: Recurrence): String {
        return Gson().toJson(recurrence)
    }

    @TypeConverter
    fun fromGsonToRecurrence(recurrence: String): Recurrence {
        return Gson().fromJson(recurrence, Recurrence::class.java)
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
    fun fromCategoryToGson(category: Category):String{
        return Gson().toJson(category)
    }

    @TypeConverter
    fun fromGsonToCategory(category:String):Category{
        return Gson().fromJson(category, Category::class.java)
    }
}