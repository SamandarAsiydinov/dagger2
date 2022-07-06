package com.sdk.dagger2.utils

import com.sdk.dagger2.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

sealed class UserResource {
    object Init: UserResource()
    object Loading: UserResource()
    data class Success(val users: List<UserEntity>): UserResource()
    data class Error(val message: String): UserResource()
}
