package com.example.wordy.data

import com.example.wordy.WordData
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getDataFromApi(word: String) :Single<List<WordData>>
}