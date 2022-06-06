package com.example.wordy

import android.app.Application
import android.content.Context
import com.example.wordy.dependencies.AppDependenciesComponent
import com.example.wordy.dependencies.DaggerAppDependenciesComponent
import com.example.wordy.dependencies.DaggerModule

class WordyApp: Application() {

    companion object  {
        lateinit var instance: WordyApp
    }

    lateinit var appDependenciesComponent: AppDependenciesComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appDependenciesComponent = DaggerAppDependenciesComponent
            .builder()
            .daggerModule(DaggerModule(this))
            .build()
    }
}

val Context.app: WordyApp
    get() = applicationContext as WordyApp