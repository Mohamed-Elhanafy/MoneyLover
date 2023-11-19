package com.example.monylover.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.monylover.models.Expense

@Dao
interface ExpensesDao {
    @Query("SELECT * FROM expenses")
    fun getAll(): List<Expense>

    @Insert
    fun insertExpense(vararg expense: Expense)

    @Delete
    fun delete(expense: Expense)

    //Delete all expenses
    @Query("DELETE FROM expenses")
    fun deleteAll()
}