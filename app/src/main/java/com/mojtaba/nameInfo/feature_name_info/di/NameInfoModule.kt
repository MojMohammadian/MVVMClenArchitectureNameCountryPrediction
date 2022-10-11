package com.mojtaba.nameInfo.feature_name_info.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.mojtaba.nameInfo.feature_name_info.data.local.Converters
import com.mojtaba.nameInfo.feature_name_info.data.local.NameInfoDao
import com.mojtaba.nameInfo.feature_name_info.data.local.NameInfoDatabase
import com.mojtaba.nameInfo.feature_name_info.data.remote.NameInfoApi
import com.mojtaba.nameInfo.feature_name_info.data.repository.NameInfoRepositoryImpl
import com.mojtaba.nameInfo.feature_name_info.data.util.GsonParser
import com.mojtaba.nameInfo.feature_name_info.domain.repository.NameInfoRepository
import com.mojtaba.nameInfo.feature_name_info.domain.use_case.GetNameInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NameInfoModule {

    @Provides
    @Singleton
    fun getNameInfoUseCase(repository : NameInfoRepository) : GetNameInfo {
        return GetNameInfo(repository)
    }


    @Provides
    @Singleton
    fun getNameInfoRepository(
        api : NameInfoApi,
        db: NameInfoDatabase
    ): NameInfoRepository{
        return NameInfoRepositoryImpl(api, db.nameInfoDao)
    }


    @Provides
    @Singleton
    fun getNameInfoDatabase(app : Application): NameInfoDatabase{
        return Room.databaseBuilder(app,NameInfoDatabase::class.java,"nameInfoDb")
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun getNameInfoApi(): NameInfoApi{
        return Retrofit.Builder()
            .baseUrl(NameInfoApi.NAME_BASE_INFO_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NameInfoApi::class.java)
    }


}