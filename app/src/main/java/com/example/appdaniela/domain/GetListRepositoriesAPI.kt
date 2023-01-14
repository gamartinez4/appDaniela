package com.example.appdaniela.domain

import com.example.appdaniela.repository.interfaces.IntroRepository
import com.example.appdaniela.viewModels.IntroViewModel

class GetListGitReposAPI(private val introRepository: IntroRepository) {
    suspend fun execute(viewModel: IntroViewModel) = introRepository.getRepos(viewModel)
}