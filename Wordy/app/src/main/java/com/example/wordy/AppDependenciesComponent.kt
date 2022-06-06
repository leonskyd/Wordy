package com.example.wordy.dependencies

import com.example.wordy.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[DaggerModule::class])

interface AppDependenciesComponent {
    fun inject(activity: MainActivity)
}