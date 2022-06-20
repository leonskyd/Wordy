package com.example.wordy.data


import com.example.wordy.WordData

interface Repository {
    suspend fun getDataFromApi(
        word: String) : List<WordData>
}