package com.example.monylover.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monylover.data.db.RoomDb
import com.example.monylover.models.Category
import com.example.monylover.models.Expense
import com.example.monylover.models.Recurrence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime

data class AddScreenState(
    val amount: String = "0",
    val recurrence: Recurrence = Recurrence.None,
    val date: LocalDateTime? = null,
    val note: String = "",
    val category: String? = null, // TODO: Change to Category when build category model
)

class AddViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(AddScreenState())
    val uiState: StateFlow<AddScreenState> = _uiState.asStateFlow()

    fun setAmount(amount: String) {
        var parsed = amount.toDoubleOrNull()

        if (amount.isEmpty()) {
            parsed = 0.0
        }

        if (parsed != null) {
            _uiState.update { currentState ->
                currentState.copy(
                    amount = amount.trim().ifEmpty { "0" },
                )
            }
        }
    }

    fun setRecurrence(recurrence: Recurrence) {
        _uiState.update { currentState ->
            currentState.copy(
                recurrence = recurrence,
            )
        }
    }

    fun setDate(date: LocalDateTime) {
        _uiState.update { currentState ->
            currentState.copy(
                date = date,
            )
        }
    }

    fun setNote(note: String) {
        _uiState.update { currentState ->
            currentState.copy(
                note = note,
            )
        }
    }

    fun setCategory(category: String) {
        _uiState.update { currentState ->
            currentState.copy(
                category = category,
            )
        }
    }



    fun submitExpense(database:RoomDb) {
        viewModelScope.launch(Dispatchers.IO) {
            database.databaseDao().insertExpense(
                Expense(
                    amount = uiState.value.amount.toDouble(),
                    recurrence = uiState.value.recurrence,
                    date = uiState.value.date!!,
                    note = uiState.value.note,
                    category = Category(name = uiState.value.category!!, color = Color.White.toArgb()),
                )
            )
        }
    }
}