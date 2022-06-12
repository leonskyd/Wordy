package com.example.wordy.data

import com.example.wordy.WordData

class RetrofitRepository
    (private val api: FreeDictionaryApi): Repository {

   override suspend fun getDataFromApi(
       word: String) : List<WordData> {
            val result = api.getDefinitionsAndSynonyms(word)
            return result.body()!!
   }
}