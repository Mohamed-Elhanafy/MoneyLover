package com.example.monylover.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.monylover.data.db.Converters
import com.example.monylover.ui.theme.Primary

@Entity(tableName = "categories")
@TypeConverters(Converters::class)

data class Category (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "color")
    var color: Int = Primary.toArgb() // Store the color as Int

)