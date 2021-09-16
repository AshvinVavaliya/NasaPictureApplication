package com.exercise.nasapictures

import android.app.Application
import com.exercise.nasapictures.module.mainModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(mainModules)
        }
    }
}