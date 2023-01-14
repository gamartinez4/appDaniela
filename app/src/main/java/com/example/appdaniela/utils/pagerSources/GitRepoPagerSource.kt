package com.example.appdaniela.utils.pagerSources

import android.util.Log
import androidx.paging.*
import com.example.appdaniela.models.*
import com.example.appdaniela.remote.IntroRepoDataSource
import com.example.appdaniela.utils.roomDb.daos.RemoteKeyDao
import com.example.appdaniela.utils.roomDb.daos.RoomModelDao
import com.example.appdaniela.viewModels.IntroViewModel
import retrofit2.HttpException
import java.io.IOException



@ExperimentalPagingApi
class PagerMediator(
    private val roomModelDao: RoomModelDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val introRepoDataSource: IntroRepoDataSource,
    private val deleteNoneFavouriteItemsFun:()->Boolean,
    private val setDeleteNoneFavouriteItemsFlag:(value:Boolean)->Unit
) : RemoteMediator<Int, RoomModel>(){

    override suspend fun initialize(): InitializeAction = InitializeAction.LAUNCH_INITIAL_REFRESH


    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, RoomModel>
    ): MediatorResult {
        val offset = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            if ((loadType == LoadType.REFRESH && remoteKeyDao.isEmpty()) || (loadType == LoadType.APPEND && !deleteNoneFavouriteItemsFun())) {
                setDeleteNoneFavouriteItemsFlag(false)
                val response = introRepoDataSource.getPosts(start = offset.toString())
                var isEndOfList = false
                when (response) {

                    is Result.Success -> {
                        //   if (loadType == LoadType.REFRESH) {
                        //     roomModelDao.deleteAll()
                        //   remoteKeyDao.deleteAll()
                        // }
                        val prevKey = if (offset == 0) null else offset - 10
                        val nextKey = offset + 10
                        val modelsList = response.data
                        val keys = modelsList.map {
                            RemoteKey(it.id, prevKey = prevKey, nextKey = nextKey)
                        }
                        remoteKeyDao.insertAll(keys)
                        roomModelDao.insertAll(modelsList.map { it.gitRepListInfo2RoomModel() })
                    }
                    is Result.Failure -> {
                        Log.e("TransactionsMediator", response.exception.toString())
                        isEndOfList = true
                    }
                }
                return MediatorResult.Success(endOfPaginationReached = isEndOfList)
            }
            return MediatorResult.Success(endOfPaginationReached = true)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, RoomModel>): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(10) ?: 0
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                return nextKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int,RoomModel>): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                remoteKeyDao.remoteKeysId(repoId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, RoomModel>): RemoteKey? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { element -> remoteKeyDao.remoteKeysId(element.id) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, RoomModel>): RemoteKey? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { element -> remoteKeyDao.remoteKeysId(element.id) }
    }


}