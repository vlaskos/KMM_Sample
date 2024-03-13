package com.example.kmm_app.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class CacheUser() : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var url: String = ""
    var login: String = ""
    var avatarURL: String = ""
    constructor(id: Int = 0, login: String = "", avatarURL: String = "" ,url: String = "") : this() {
        this.id = id
        this.login = login
        this.avatarURL = avatarURL
        this.url = url
    }
}