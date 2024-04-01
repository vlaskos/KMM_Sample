package com.example.kmm_app.models
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitUser (
    @SerialName("id")
    var id: Int,
    @SerialName("url")
    var url: String,
    @SerialName("login")
    var login: String,
    @SerialName("avatar_url")
    var avatarURL: String
)

