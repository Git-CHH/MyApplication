package com.example.examapplication

import android.app.Application
import android.content.Context
import com.example.examapplication.di.allAppModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin context
        startKoin(this, allAppModule)

    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}