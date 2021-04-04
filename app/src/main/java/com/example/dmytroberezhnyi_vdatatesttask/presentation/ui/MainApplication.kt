package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui

import android.app.Application
import com.example.dmytroberezhnyi_vdatatesttask.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}