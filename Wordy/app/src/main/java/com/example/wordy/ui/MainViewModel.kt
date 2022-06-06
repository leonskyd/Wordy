package com.example.wordy.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wordy.WordData
import com.example.wordy.data.Repository
import com.example.wordy.model.DataToShow
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private var rertofitRepository: Repository
): ViewModel()

{
    lateinit var liveDataList: MutableLiveData<DataToShow?>
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    init {
        liveDataList = MutableLiveData()
    }
    fun getLiveDataObserver(): MutableLiveData<DataToShow?> = liveDataList

    fun makeCall(word: String) {
        compositeDisposable.add(
            rertofitRepository
                .getDataFromApi(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    transmitDataToShow(it)
                }
        )
    }

    private fun transmitDataToShow(it: List<WordData>) {
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

class MainViewModelFactory (private val repository: Repository)
    : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}