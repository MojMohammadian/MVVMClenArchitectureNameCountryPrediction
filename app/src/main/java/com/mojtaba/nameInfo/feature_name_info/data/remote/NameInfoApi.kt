package com.mojtaba.nameInfo.feature_name_info.data.remote

import com.mojtaba.nameInfo.feature_name_info.data.remote.dto.NameInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NameInfoApi {

    @GET(".")
    suspend fun getNameInfo(
        @Query("name") name: String
    ): NameInfoDto

    companion object {
        val NAME_BASE_INFO_URL = "https://api.nationalize.io/"
    }
}
