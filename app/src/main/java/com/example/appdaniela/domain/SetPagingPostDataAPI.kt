package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.IntroRepository

class SetPagingPostDataAPI(private val introRepository: IntroRepository) {
    suspend fun execute(
        deleteNoneFavouriteItemsFun:()->Boolean,
        setDeleteNoneFavouriteItemsFlag:(value:Boolean)->Unit
    ) = introRepository.setPagingPostData(deleteNoneFavouriteItemsFun,setDeleteNoneFavouriteItemsFlag)
}