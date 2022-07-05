package com.example.wordy

import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getDataFromApi(word: String) :Single<List<WordData>>
}