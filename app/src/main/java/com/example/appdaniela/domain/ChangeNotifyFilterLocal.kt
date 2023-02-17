package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.IntroRepository

class ChangeNotifyFilterLocal(private var introRepository: IntroRepository) {
        suspend fun execute(filter:String) = introRepository.changeNotifyFilter(filter)
}