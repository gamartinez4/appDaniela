package com.example.appdaniela.koinModules

import android.content.Context
import com.example.appdaniela.domain.*
import com.example.appdaniela.viewModels.DetailsViewModel
import com.example.appdaniela.viewModels.IntroViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val introModule = module {
    factory { SetPagingFoodDataAPI(get()) }
    factory { DeleteNoneFavouriteFoodsLocal(get()) }
    factory { DeleteAllFoodsLocal(get()) }
    factory { DeleteAllKeysLocal(get()) }
    factory { ChangeNotifyFilterLocal(get()) }
    viewModel { IntroViewModel(get(),get(),get(),get(),get()) }
}

val detailsModule = module {
    factory { SetFoodDtoLocal(get()) }
    factory { DeleteFoodLocal(get()) }
    viewModel { DetailsViewModel(get(),get()) }
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