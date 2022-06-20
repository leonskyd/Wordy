package com.example.wordy.database

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [WordEntity::class],
    version = 1,
    exportSchema = false) // true - история изменений БД
abstract class SavedWordsDatabase: RoomDatabase() {
    abstract fun getWordDao(): WordDao
}

