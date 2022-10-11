package com.mojtaba.nameInfo.feature_name_info.presentation

import com.mojtaba.nameInfo.feature_name_info.domain.model.NameInfo

data class NameInfoState (
    val nameInfoItem: NameInfo? = null,
    val isLoading: Boolean = false
)