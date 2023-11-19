package com.example.monylover.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.monylover.models.Category
import com.example.monylover.models.Expense

@Dao
interface MyDao {
    @Query("SELECT * FROM expenses")
    fun getAllExpenses(): List<Expense>

    @Insert
    fun insertExpense(vararg expense: Expense)

    @Delete
    fun deleteExpense(expense: Expense)

    //Delete all expenses
    @Query("DELETE FROM expenses")
    fun deleteAllExpenses()

    @Insert
    fun insertCategory(vararg category: Category)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): List<Category>

    @Delete
    fun deleteCategory(category: Category)

    @Query("DELETE FROM categories")
    fun deleteAllCategories()
}