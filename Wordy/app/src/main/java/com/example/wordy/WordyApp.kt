package com.example.wordy

import android.app.Application
import android.content.Context
import com.example.wordy.dependencies.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WordyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WordyApp)
            modules(appModule)
        }
    }
}

val Context.app: WordyApp
    get() = applicationContext as WordyApp