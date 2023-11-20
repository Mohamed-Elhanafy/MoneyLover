package com.example.monylover.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monylover.data.db.RoomDb
import com.example.monylover.models.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ReportsState(
    val expanses: List<Expense> = listOf()
)
class ReportsViewModel:ViewModel() {

    private val _uiState = MutableStateFlow(ReportsState())
    val uiState: StateFlow<ReportsState> = _uiState.asStateFlow()


    fun getExpenses(database: RoomDb) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {currentState ->
                currentState.copy(database.databaseDao().getAllExpenses())
            }
        }
    }
}