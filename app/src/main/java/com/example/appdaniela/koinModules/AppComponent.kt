package com.example.appdaniela.koinModules

import android.content.Context
import com.example.appdaniela.domain.GetListGitReposAPI
import com.example.appdaniela.viewModels.IntroViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val introModule = module {
    factory { GetListGitReposAPI(get()) }
    viewModel { IntroViewModel(get()) }
}

fun appComponent(baseUrlApiWallet: String, context: Context): List<Module> {
    return listOf(
        createRemoteModule(baseUrlApiWallet),
        repositoryModule,
        localModule,
        introModule
    )
}