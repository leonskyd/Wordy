package com.example.wordy.data

import com.example.wordy.WordData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FreeDictionaryApi {
    @GET("api/v2/entries/en/{word}")
    suspend fun getDefinitionsAndSynonyms (
        @Path("word")word: String)
    : Response<List<WordData>> }




