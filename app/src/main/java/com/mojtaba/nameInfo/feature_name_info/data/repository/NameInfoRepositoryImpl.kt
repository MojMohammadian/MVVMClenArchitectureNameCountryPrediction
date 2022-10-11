package com.mojtaba.nameInfo.feature_name_info.data.repository

import com.mojtaba.nameInfo.core.Resource
import com.mojtaba.nameInfo.feature_name_info.data.local.NameInfoDao
import com.mojtaba.nameInfo.feature_name_info.data.local.NameInfoDatabase
import com.mojtaba.nameInfo.feature_name_info.data.remote.NameInfoApi
import com.mojtaba.nameInfo.feature_name_info.domain.model.NameInfo
import com.mojtaba.nameInfo.feature_name_info.domain.repository.NameInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NameInfoRepositoryImpl(
    private val api : NameInfoApi,
    private val dao : NameInfoDao
) : NameInfoRepository{

    override fun getNameInfo(name: String): Flow<Resource<NameInfo>> = flow {
        emit(Resource.Loading())
        val nameInfo= dao.getNameInfo(name)?.toNameInfo()
        emit(Resource.Loading(data = nameInfo))
        try {
            val remoteNameInfo = api.getNameInfo(name)
            dao.deleteFromNameInfo(name)
            dao.insertNamedInfo(remoteNameInfo.toNameInfoEntity())
        }
        catch (e:HttpException){
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = nameInfo
            ))
        }
        catch (e:IOException){
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = nameInfo
            ))
        }

        val newNameInfo = dao.getNameInfo(name)?.toNameInfo()
        emit(Resource.Success(data = newNameInfo))
    }

}