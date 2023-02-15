package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.IntroRepository

class DeleteNoneFavouriteFoodsLocal (private val introRepository: IntroRepository) {
    suspend fun execute() = introRepository.deleteAllNoneFavourite()
}