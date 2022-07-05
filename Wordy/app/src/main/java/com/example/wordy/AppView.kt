package com.example.wordy

interface AppView {
    fun showSuccess(
        definitions: StringBuilder,
        synonyms: String
    )
    fun showError()
    fun showSearch()
}