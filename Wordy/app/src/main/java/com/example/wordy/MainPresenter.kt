package com.example.wordy

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.StringBuilder


class MainPresenter(
    protected val repository: Repository = RetrofitRepository(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) {

    private var view: AppView? = null

    fun onAttach(view: AppView) {
        this.view = view
    }

    fun getContent(word: String){
      compositeDisposable.add( // вот эту всю логику надо будет отправить в Интерактор
          repository
              .getDataFromApi(word)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeBy {
                  val synonyms = it[0].meanings[0].synonyms.toString()
                  val definitionsArray = it[0].meanings[0].definitions
                  val definitions = StringBuilder()
                  for (item in definitionsArray) {
                      definitions.append(item.definition)
                      definitions.append("\n")
                  }
                  view?.showSuccess(definitions,synonyms)}
      )
    }

    fun onDetach(view: AppView) {
        compositeDisposable.clear()
    }

}