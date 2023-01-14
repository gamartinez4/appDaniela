package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.IntroRepository

class DeleteAllKeysLocal (private val introRepository: IntroRepository) {
    suspend fun execute() = introRepository.deleteAllKeys()
}