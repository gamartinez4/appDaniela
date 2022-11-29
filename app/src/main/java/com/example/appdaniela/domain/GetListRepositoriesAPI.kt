package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.IntroRepository

class GetListGitReposAPI(private var introRepository: IntroRepository) {
    suspend fun execute() = introRepository.getRepos()
}