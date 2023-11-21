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
import java.time.LocalDateTime

data class ReportsScreenStatus(
    val expansesList:List<Expense> = listOf(),

)
class ReportsViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(ReportsScreenStatus())
    val uiState: StateFlow<ReportsScreenStatus> = _uiState.asStateFlow()


    fun getExpanses(database:RoomDb){
        viewModelScope.launch(Dispatchers.IO){
            _uiState.update { currentState->
                currentState.copy(
                    expansesList = database.databaseDao().getAllExpenses()
                )
            }
        }
    }
}