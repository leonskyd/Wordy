package com.example.wordy

import com.google.gson.annotations.SerializedName

data class WordData(
    @SerializedName("word") val word: String? = null,
    @SerializedName("meanings") val meanings: ArrayList<Meanings> = arrayListOf()
)

data class Meanings(
    @SerializedName("partOfSpeech") val partOfSpeech: String? = null,
    @SerializedName("definitions") val definitions: ArrayList<Definitions> = arrayListOf(),
    @SerializedName("synonyms") val synonyms: ArrayList<String> = arrayListOf(),
    @SerializedName("antonyms") val antonyms: ArrayList<String> = arrayListOf()
)

data class Definitions(
    @SerializedName("definition") val definition: String? = null,
    @SerializedName("synonyms") val synonyms: ArrayList<String> = arrayListOf(),
    @SerializedName("antonyms") val antonyms: ArrayList<String> = arrayListOf()
)

