package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.IntroRepository
import okhttp3.internal.wait

class DeleteNoneFavouriteItemsLocal (private val introRepository: IntroRepository) {
    suspend fun execute() = introRepository.deleteAllNoneFavourite()
}