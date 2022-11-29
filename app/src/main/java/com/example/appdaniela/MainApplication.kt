package com.example.appdaniela

import android.app.Application
import android.content.Context
import com.example.appdaniela.koinModules.appComponent
import com.example.appdaniela.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication  : Application(){
    override fun onCreate(){
        super.onCreate()
        initKoin(this)
    }

    fun initKoin(context: Context) {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(appComponent(Constants.URL_BASE, context))
        }
    }
}