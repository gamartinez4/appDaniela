package com.example.appdaniela.koinModules

import com.example.appdaniela.utils.roomDb.DataBaseProject
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { DataBaseProject.buildDatabase(androidContext()) }
    factory { (get() as DataBaseProject).getKeysDao() }
    factory { (get() as DataBaseProject).getRoomModelDao()}
}