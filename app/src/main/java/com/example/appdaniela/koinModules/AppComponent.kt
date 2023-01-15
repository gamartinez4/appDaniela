package com.example.appdaniela.koinModules

import android.content.Context
import com.example.appdaniela.domain.*
import com.example.appdaniela.viewModels.DetailsViewModel
import com.example.appdaniela.viewModels.IntroViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val introModule = module {
    factory { SetPagingPostDataAPI(get()) }
    factory { DeleteNoneFavouriteItemsLocal(get()) }
    factory { DeleteAllPostsLocal(get()) }
    factory { DeleteAllKeysLocal(get()) }
    factory { SetCommentsLocal(get()) }
    factory { GetAllCommentsAPI(get()) }
    factory { GetAllUsersAPI(get()) }
    factory { SetUsersLocal(get()) }
    viewModel { IntroViewModel(get(),get(),get(),get(),get(),get(),get(),get()) }
}

val detailsModule = module {
    factory { SetPostDtoLocal(get()) }
    factory { SetPagingCommentDataAPI(get()) }
    factory { GetUserFromUserIdLocal(get()) }
    factory { DeleteUserLocal(get()) }
    viewModel { DetailsViewModel(get(),get(),get(),get()) }
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