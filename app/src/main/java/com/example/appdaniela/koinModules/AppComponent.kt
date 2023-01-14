package com.example.appdaniela.koinModules

import android.content.Context
import com.example.appdaniela.domain.*
import com.example.appdaniela.viewModels.DetailsViewModel
import com.example.appdaniela.viewModels.IntroViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val introModule = module {
    factory { GetListGitReposAPI(get()) }
    factory { DeleteNoneFavouriteItemsLocal(get()) }
    factory { DeleteAllItemsLocal(get()) }
    factory { DeleteAllKeysLocal(get()) }
    viewModel { IntroViewModel(get(),get(),get(),get()) }
}

val detailsModule = module {
    factory { SetRoomModelLocal(get()) }
    viewModel { DetailsViewModel(get()) }
}

fun appComponent(baseUrlApiWallet: String, context: Context): List<Module> {
    return listOf(
        createRemoteModule(baseUrlApiWallet),
        repositoryModule,
        localModule,
        introModule,
        detailsModule
    )
}