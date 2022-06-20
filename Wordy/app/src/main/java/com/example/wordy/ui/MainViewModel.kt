package com.example.wordy.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordy.WordData
import com.example.wordy.data.Repository
import com.example.wordy.model.DataToShow
import kotlinx.coroutines.*

class MainViewModel(
    private var retrofitRepository: Repository,
    private var interactor: DatabaseInteractor
): ViewModel()

{
    lateinit var liveDataList: MutableLiveData<DataToShow?>
    init {
        liveDataList = MutableLiveData()
    }
    fun getLiveDataObserver(): MutableLiveData<DataToShow?> = liveDataList

    fun makeCall(word: String) = viewModelScope.launch(Dispatchers.IO) {
        val dataList = retrofitRepository.getDataFromApi(word)
        delay(1000)
        transmitDataToShow(dataList)
    }

    private suspend fun transmitDataToShow(it: List<WordData>) {
        withContext(Dispatchers.Main) {
            val synonyms = it[0].meanings[0].synonyms.toString()
            val definitionsArray = it[0].meanings[0].definitions
            val definitionBuilder = StringBuilder()
            for (item in definitionsArray) {
                definitionBuilder.append(item.definition)
                definitionBuilder.append("\n")
            }
            val definitions = definitionBuilder.toString()
            val dataToShow = DataToShow(definitions, synonyms)
            liveDataList.postValue(dataToShow)
        }
    }

    fun saveWord(word: String) = viewModelScope.launch(Dispatchers.IO) {
        interactor.saveWordToDb(word)
    }

    fun getAllFromDatabase(): List<WordEntity> {
        return interactor.getWordsList()
    }


}
