package com.example.appdaniela.utils.remoteMediators

import android.util.Log
import androidx.paging.*
import com.example.appdaniela.models.*
import com.example.appdaniela.remote.IntroDataSource
import com.example.appdaniela.utils.roomDb.daos.RemoteKeyDao
import com.example.appdaniela.utils.roomDb.daos.FoodsDao
import retrofit2.HttpException
import java.io.IOException



@ExperimentalPagingApi
class PostRemoteMediator(
    private val foodsDao: FoodsDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val introRepoDataSource: IntroDataSource,
    private val deleteNoneFavouriteItemsFun:()->Boolean,
    private val setDeleteNoneFavouriteItemsFlag:(value:Boolean)->Unit
) : RemoteMediator<Int, FoodModDto>(){

    override suspend fun initialize(): InitializeAction = InitializeAction.LAUNCH_INITIAL_REFRESH


    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, FoodModDto>
    ): MediatorResult {
        try {
            if ((loadType == LoadType.REFRESH && remoteKeyDao.isEmpty()) ) {
                setDeleteNoneFavouriteItemsFlag(false)
                val response = introRepoDataSource.getFoods()
                var isEndOfList = false
                when (response) {
                    is Results.Success -> {
                        val modelsList = response.data
                        val keys = modelsList.map {
                            RemoteKey(it.id, prevKey = null, nextKey = null)
                        }
                        remoteKeyDao.insertAll(keys)
                        foodsDao.insertAll(modelsList.map { it.foodMod2foodModDto() })
                    }
                    is Results.Failure -> {
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
}