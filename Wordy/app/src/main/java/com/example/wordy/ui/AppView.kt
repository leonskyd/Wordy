package com.example.wordy.ui

import com.example.wordy.model.DataToShow

interface AppView {
    fun showSuccess(
        dataToShow: DataToShow?
    )
    fun showError()
    fun showSearch()
}