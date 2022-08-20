package com.example.quicknote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QuickNotesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}