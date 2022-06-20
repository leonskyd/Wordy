package com.example.wordy.database

import androidx.room.*

@Dao
interface WordDao {
    @Query("SELECT * FROM saved_words_table")
    fun getAllWords(): List<WordEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveWord(word: WordEntity)

    @Delete
    fun deleteEntity(word: WordEntity)


}