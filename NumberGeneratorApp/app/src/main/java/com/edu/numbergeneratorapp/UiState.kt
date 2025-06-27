package com.edu.numbergeneratorapp

sealed class UiState {
    data object Idle : UiState()
    data object Loading : UiState()
    data class Success(val number: Int) : UiState()
    data class Error(val message: String) : UiState()
}