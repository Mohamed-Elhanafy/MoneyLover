package com.example.monylover.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monylover.data.db.RoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingViewModel:ViewModel() {


    fun deleteAllData(database:RoomDb) {
        viewModelScope.launch(Dispatchers.IO) {
            database.databaseDao().deleteAllExpenses()
            database.databaseDao().deleteAllCategories()
        }
    }
}