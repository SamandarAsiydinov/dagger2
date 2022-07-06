package com.sdk.dagger2.database.dao

import androidx.room.*
import com.sdk.dagger2.database.entity.UserEntity
import com.sdk.dagger2.model.User
import com.sdk.dagger2.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveUser(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(users: List<UserEntity>)

    @Query("SELECT * FROM ${Constants.TABLE_NAME} ORDER BY id ASC")
    suspend fun getUsers(): List<UserEntity>

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM ${Constants.TABLE_NAME}")
    suspend fun deleteAll()
}