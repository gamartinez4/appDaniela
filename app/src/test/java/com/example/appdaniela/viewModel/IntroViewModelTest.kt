package com.example.appdaniela.viewModel

import androidx.paging.PagingData
import com.example.appdaniela.domain.SetPagingPostDataAPI
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

    private lateinit var getListGitReposAPI: SetPagingPostDataAPI

    @Mock
    private lateinit var introRepository:IntroRepository

    @Before
    fun setUp() {
        getListGitReposAPI = SetPagingPostDataAPI(introRepository)
        viewModel = IntroViewModel(getListGitReposAPI)

    }

    @Test
    fun getTransactionsList() =

        mainCoroutineRule.runBlockingTest {
            val pagingData: Flow<PagingData<GitRepListInfo>> = flow {
                mockedGit
            }
            given(introRepository.setPagingPostData()).willReturn(pagingData)

            val result = getListGitReposAPI.execute()

            result shouldEqual pagingData
            verify(introRepository).setPagingPostData()
        }
    
}