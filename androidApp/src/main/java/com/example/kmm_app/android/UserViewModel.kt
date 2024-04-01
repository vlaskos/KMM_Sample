package com.example.kmm_app.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmm_app.SharedService
import com.example.kmm_app.models.GitUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    data class UserListState(
        val allUsers: List<GitUser> = emptyList()
    )

    private val _state = MutableStateFlow(
        UserListState(
            allUsers = emptyList()
        )
    )

    init {
        viewModelScope.launch {
            _state.value = UserListState(
                allUsers = SharedService().fetchUsers()
            )
        }
    }

    val state: StateFlow<UserListState> = _state.asStateFlow()
}