package com.example.wordy.data

import androidx.lifecycle.MutableLiveData
import com.example.wordy.WordData
import io.reactivex.rxjava3.core.Single

interface Repository {
    suspend fun getDataFromApi(
        word: String) : List<WordData>
}