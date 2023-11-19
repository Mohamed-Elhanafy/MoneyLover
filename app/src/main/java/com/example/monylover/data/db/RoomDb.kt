package com.example.monylover.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.monylover.models.Category
import com.example.monylover.models.Expense

@Database(entities = [Expense::class ,Category::class ], version = 2)
@TypeConverters(Converters::class )
abstract class RoomDb: RoomDatabase() {
    abstract fun databaseDao(): MyDao

    companion object {
        private const val DATABASE_NAME = "money_lover_database"

        private var INSTANCE: RoomDb? = null
        fun getDatabase(context: Context): RoomDb {
            if (INSTANCE == null) {
                INSTANCE = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE!!
        }
    }

}