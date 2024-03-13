package com.example.kmm_app.providers

import com.example.kmm_app.models.CacheUser
import com.example.kmm_app.models.GitUser
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query

interface DatabaseService {
    suspend fun save(users: List<GitUser>)
    suspend fun fetchUsers(): List<GitUser>
}

class DatabaseServiceImp : DatabaseService {

    private val config = RealmConfiguration.create(schema = setOf(CacheUser::class))
    private val realm: Realm = Realm.open(config)

    override suspend fun save(users: List<GitUser>) {
        cleanRealm()
        val cacheUsers = users.map { user ->
            CacheUser(user.id, user.url, user.login, user.avatarURL)
        }
        realm.write {
            cacheUsers.map { copyToRealm(it) }
        }
    }

    override suspend fun fetchUsers(): List<GitUser> {
        return realm.query<CacheUser>().find().map { user: CacheUser ->
            GitUser(user.id, user.url, user.login, user.avatarURL)
        }
    }

    private suspend fun cleanRealm() {
        realm.write {
            deleteAll()
        }
    }
}



