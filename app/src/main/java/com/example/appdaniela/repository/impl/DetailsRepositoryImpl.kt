package com.example.appdaniela.repository.impl


import com.example.appdaniela.repository.interfaces.DetailsRepository
import com.example.appdaniela.utils.roomDb.daos.FoodsDao


class DetailsRepositoryImpl(
    private val postDtoDao: FoodsDao
) : DetailsRepository {

    override suspend fun setFood(favourite:Boolean, id:String) {
        postDtoDao.insertElement(favourite,id)
    }

    override suspend fun deleteFood(id:String){
        postDtoDao.deletePost(id)
    }

}