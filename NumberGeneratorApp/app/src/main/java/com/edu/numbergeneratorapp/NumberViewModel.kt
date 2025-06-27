package com.edu.numbergeneratorapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class NumberViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    fun generateRandomNumber() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                delay(2000)
                val randomNumber = Random.nextInt(1, 101)
                if (randomNumber % 5 == 0) {
                    throw RuntimeException("Erro ao gerar número (múltiplo de 5)")
                }
                _uiState.value = UiState.Success(randomNumber)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Falha: ${e.message}")
            }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Idle
    }
}