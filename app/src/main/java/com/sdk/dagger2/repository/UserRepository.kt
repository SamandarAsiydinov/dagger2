package com.sdk.dagger2.repository

import com.sdk.dagger2.database.dao.UserDao
import com.sdk.dagger2.database.entity.UserEntity
import com.sdk.dagger2.network.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {

    suspend fun getRemoteUsers() = flow { emit(apiService.getUsers()) }

    suspend fun getLocalUsers() = userDao.getUsers()
    suspend fun addUser(userEntity: UserEntity) = userDao.saveUser(userEntity)
    suspend fun addAllUsers(users: List<UserEntity>) = userDao.saveAll(users)
    suspend fun deleteUser(userEntity: UserEntity) = userDao.deleteUser(userEntity)
    suspend fun deleteAllUsers() = userDao.deleteAll()
}