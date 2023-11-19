package com.example.monylover.data.db

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson

class CategoryConverters {

    @TypeConverter
    fun fromColorToGson(color: Color): String {
        return Gson().toJson(color)
    }

    @TypeConverter
    fun fromGsonToColor(color: String): Color {
        return Gson().fromJson(color, Color::class.java)
    }
}