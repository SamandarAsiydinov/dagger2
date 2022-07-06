package com.sdk.dagger2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sdk.dagger2.database.dao.UserDao
import com.sdk.dagger2.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}