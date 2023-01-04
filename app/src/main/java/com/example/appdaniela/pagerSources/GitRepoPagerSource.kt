package com.example.appdaniela.pagerSources

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.appdaniela.models.GitRepListInfo
import com.example.appdaniela.models.GitRepModel
import com.example.appdaniela.models.Result
import com.example.appdaniela.proxy.ApiServices
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class GitRepoPagerSource (
    private val apiServices: ApiServices
    ) : PagingSource<Int, GitRepListInfo>() {

    private var result: GitRepModel? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitRepListInfo> {
        return try {
            val pageNumber = params.key ?: 1
            val response = apiServices.getRepos(page = pageNumber.toString())
            if(response.isSuccessful && response.body()!=null) {
                result = response.body() as GitRepModel
                LoadResult.Page(
                    data = result!!.items,
                    prevKey = null,
                    nextKey = pageNumber+1
                )
            }
            else {
                LoadResult.Page(
                    data = ArrayList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            Log.e("servicio",e.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GitRepListInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}