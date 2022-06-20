package com.example.wordy.database

class DatabaseInteractor(
    val wordDataObject: WordDao
) {

    fun getWordsList() = wordDataObject.getAllWords()

    fun saveWordToDb(word: String) = wordDataObject.saveWord(
        WordEntity(id = 0, wordName = word))
}