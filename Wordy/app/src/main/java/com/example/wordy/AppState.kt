package com.example.wordy

sealed class AppState {
    data class Success(val data: WordData?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
