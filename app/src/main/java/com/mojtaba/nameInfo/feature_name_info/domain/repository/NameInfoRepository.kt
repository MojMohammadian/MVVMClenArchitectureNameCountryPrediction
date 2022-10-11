package com.mojtaba.nameInfo.feature_name_info.domain.repository

import com.mojtaba.nameInfo.core.Resource
import com.mojtaba.nameInfo.feature_name_info.domain.model.NameInfo
import kotlinx.coroutines.flow.Flow

interface NameInfoRepository {
    fun getNameInfo(name: String): Flow<Resource<NameInfo>>
}