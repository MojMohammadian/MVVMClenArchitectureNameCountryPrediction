package com.mojtaba.nameInfo.feature_name_info.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mojtaba.nameInfo.feature_name_info.data.local.entity.NameInfoEntity

@Database(
    entities = [NameInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class NameInfoDatabase : RoomDatabase(){

abstract val nameInfoDao : NameInfoDao

}