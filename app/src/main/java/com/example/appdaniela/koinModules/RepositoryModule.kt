package com.example.appdaniela.koinModules

import com.example.appdaniela.repository.impl.IntroRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
   factory { IntroRepositoryImpl(get(),get()) }
}