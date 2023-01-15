package com.example.appdaniela.viewModel

import androidx.paging.PagingData
import com.example.appdaniela.domain.*
import com.example.appdaniela.models.PostDto
import com.example.appdaniela.repository.interfaces.DetailsRepository
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

    private lateinit var setPagingPostDataAPI: SetPagingPostDataAPI
    private lateinit var deleteNoneFavouriteItemsLocal: DeleteNoneFavouriteItemsLocal
    private lateinit var deleteAllPostsLocal: DeleteAllPostsLocal
    private lateinit var deleteAllKeysLocal : DeleteAllKeysLocal
    private lateinit var setCommentsLocal :SetCommentsLocal
    private lateinit var getAllCommentsAPI : GetAllCommentsAPI
    private lateinit var getAllUsersAPI : GetAllUsersAPI
    private lateinit var setUsersLocal : SetUsersLocal
    private lateinit var deleteUserLocal : DeleteUserLocal

    @Mock
    private lateinit var introRepository:IntroRepository

    @Mock
    private lateinit var detailsRepository:DetailsRepository

    @Before
    fun setUp() {
        setPagingPostDataAPI = SetPagingPostDataAPI(introRepository)
        deleteNoneFavouriteItemsLocal = DeleteNoneFavouriteItemsLocal(introRepository)
        deleteAllPostsLocal = DeleteAllPostsLocal(introRepository)
        deleteAllKeysLocal = DeleteAllKeysLocal(introRepository)
        setCommentsLocal = SetCommentsLocal(introRepository)
        getAllCommentsAPI = GetAllCommentsAPI(introRepository)
        getAllUsersAPI = GetAllUsersAPI(introRepository)
        setUsersLocal = SetUsersLocal(introRepository)
        deleteUserLocal = DeleteUserLocal(detailsRepository)
        viewModel =
            IntroViewModel(
                    setPagingPostDataAPI,
                    deleteNoneFavouriteItemsLocal,
                    deleteAllPostsLocal,
                    deleteAllKeysLocal,
                    getAllCommentsAPI,
                    setCommentsLocal,
                    getAllUsersAPI,
                    setUsersLocal
                )

    }

    @Test
    fun getTransactionsList() =

        mainCoroutineRule.runBlockingTest {
            val pagingData: Flow<PagingData<PostDto>> = flow {
                mockedGit
            }
            given(introRepository.setPagingPostData({false},{})).willReturn(pagingData)

            val result = setPagingPostDataAPI.execute({false},{})

            result shouldEqual pagingData
            verify(introRepository).setPagingPostData({false},{})
        }
    
}