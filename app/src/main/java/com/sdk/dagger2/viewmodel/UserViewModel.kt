package com.sdk.dagger2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.dagger2.database.entity.UserEntity
import com.sdk.dagger2.repository.UserRepository
import com.sdk.dagger2.utils.NetworkHelper
import com.sdk.dagger2.utils.UserResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val repository: UserRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _response: MutableStateFlow<UserResource> = MutableStateFlow(UserResource.Init)
    val response: StateFlow<UserResource> get() = _response

    init {
        getUsers()
    }

    private fun getUsers() {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                _response.value = UserResource.Loading
                repository.getRemoteUsers()
                    .catch {
                        _response.value = UserResource.Error(it.stackTraceToString())
                    }.collect {
                        if (it.isSuccessful) {
                            val list = ArrayList<UserEntity>()
                            it.body()?.forEach { user ->
                                list.add(UserEntity(user.id, user.name, user.username, user.email))
                            }
                            repository.addAllUsers(list)
                            _response.value = UserResource.Success(repository.getLocalUsers())
                        }
                    }
            }
        } else {
            _response.value = UserResource.Error("No internet connection")
        }
    }
}