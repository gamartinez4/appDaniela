package com.example.appdaniela.viewModel

import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.example.appdaniela.domain.GetListGitReposAPI
import com.example.appdaniela.models.GitRepListInfo
import com.example.appdaniela.repository.interfaces.IntroRepository
import com.example.appdaniela.viewModels.IntroViewModel
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldEqual
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class IntroViewModelTest {

    private lateinit var viewModel:IntroViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule =
        MainCoroutineRule()

    private lateinit var getListGitReposAPI: GetListGitReposAPI

    @Mock
    private lateinit var introRepository:IntroRepository

    @Before
    fun setUp() {
        getListGitReposAPI = GetListGitReposAPI(introRepository)
        viewModel = IntroViewModel(getListGitReposAPI)

    }

    @Test
    fun getTransactionsList() =

        mainCoroutineRule.runBlockingTest {
            val pagingData: Flow<PagingData<GitRepListInfo>> = flow {
                mockedGit
            }
            given(introRepository.getRepos()).willReturn(pagingData)

            val result = getListGitReposAPI.execute()

            result shouldEqual pagingData
            verify(introRepository).getRepos()
        }

    @Test
    fun validateGetPaymentsResult() {

        viewModel.validateResult(mockedGitData)

        viewModel.pagingData.getOrAwaitValue() shouldEqual mockedGit
    }
}