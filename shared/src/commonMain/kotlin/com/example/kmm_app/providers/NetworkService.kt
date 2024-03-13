package com.example.kmm_app.providers

import com.example.kmm_app.models.GitUser
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface NetworkService {
    suspend fun fetchUsers(): List<GitUser>
}

class NetworkServiceImp: NetworkService {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    override suspend fun fetchUsers(): List<GitUser> {
        return client.get("https://api.github.com/users").body()
    }
}