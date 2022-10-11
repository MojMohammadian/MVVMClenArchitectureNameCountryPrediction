package com.mojtaba.nameInfo.feature_name_info.domain.use_case

import com.mojtaba.nameInfo.core.Resource
import com.mojtaba.nameInfo.feature_name_info.domain.model.NameInfo
import com.mojtaba.nameInfo.feature_name_info.domain.repository.NameInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNameInfo(
    private val repository : NameInfoRepository
) {
    operator fun invoke(name: String) : Flow<Resource<NameInfo>>{
        if (name.isBlank()){
            return flow {  }
        }
        return repository.getNameInfo(name)
    }
}