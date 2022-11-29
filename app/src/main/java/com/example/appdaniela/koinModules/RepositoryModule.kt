package com.example.appdaniela.koinModules

import com.example.appdaniela.repository.impl.IntroRepositoryImpl
import com.example.appdaniela.repository.interfaces.IntroRepository
import org.koin.dsl.module

val repositoryModule = module {
   factory { IntroRepositoryImpl(get()) as IntroRepository }
}