package com.example.alejofila.themovies

import android.app.Application
import android.content.Context
import com.example.alejofila.themovies.di.appModule
import org.koin.android.ext.android.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}