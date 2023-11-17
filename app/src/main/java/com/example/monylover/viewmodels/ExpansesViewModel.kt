package com.example.monylover.viewmodels

import androidx.lifecycle.ViewModel
import com.example.monylover.models.Recurrence
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ExpansesState(
    val recurrence: Recurrence = Recurrence.None,
    val sumTotal: Double = 1250.98,
)

class ExpansesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExpansesState())
    val uiState : StateFlow<ExpansesState> = _uiState.asStateFlow()


    fun setRecurrence(recurrence: Recurrence) {
        _uiState.update {
            it.copy(recurrence = recurrence)
        }
    }

}