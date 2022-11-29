package com.example.appdaniela.viewModel

import androidx.paging.PagingData
import com.example.appdaniela.models.GitOwner
import com.example.appdaniela.models.GitRepListInfo

val mockedGit = listOf(
    GitRepListInfo(
        id = "1230456",
        name = "ejemplo/kotlin",
        description = "Descripcion ejemplo",
        private = "true",
        owner = GitOwner(login = "123",url = "www.github/ejemplo.com")
    ),
    GitRepListInfo(
        id = "112456",
        name = "ejemplo/java",
        description = "Descripcion ejemplo 2",
        private = "false",
        owner = GitOwner(login = "456",url = "www.github/ejemplo2.com")
    ),
    GitRepListInfo(
        id = "1230456",
        name = "ejemplo/python",
        description = "Descripcion ejemplo3",
        private = "false",
        owner = GitOwner(login = "789",url = "www.github/ejemplo3.com")
    )
)

val mockedGitData = PagingData.from(mockedGit)
