package com.example.kmm_app

import com.example.kmm_app.models.GitUser
import com.example.kmm_app.providers.UserService
import com.example.kmm_app.providers.UserServiceImp

class SharedService {
    private val userService: UserService = UserServiceImp()

    fun getUsers(): List<GitUser> {
        return userService.getStaticUsers()
    }

    suspend fun fetchUsers(): List<GitUser> {
        return userService.fetchUsers()
    }
}