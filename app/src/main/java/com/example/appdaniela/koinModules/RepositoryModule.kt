package com.example.appdaniela.koinModules

import com.example.appdaniela.repository.impl.DetailsRepositoryImpl
import com.example.appdaniela.repository.impl.IntroRepositoryImpl
import com.example.appdaniela.repository.interfaces.DetailsRepository
import com.example.appdaniela.repository.interfaces.IntroRepository
import com.example.appdaniela.utils.roomDb.DataBaseProject
import org.koin.dsl.module

val repositoryModule = module {
   factory { IntroRepositoryImpl(get(),get(),get(),get(),get())  as IntroRepository }
   factory { DetailsRepositoryImpl(get(),get(),get())  as DetailsRepository }
}