package com.example.kmm_app.providers
import com.example.kmm_app.models.GitUser

interface UserService {
    fun getStaticUsers(): List<GitUser>
    suspend fun fetchUsers(): List<GitUser>
}

class UserServiceImp: UserService {

    private val network: NetworkService = NetworkServiceImp()
    private val database: DatabaseService = DatabaseServiceImp()
    override fun getStaticUsers(): List<GitUser> {
        return listOf(
            GitUser(1, "url1", "name1", "avatarUrl1"),
            GitUser(2, "url2", "name2", "avatarUrl2"),
            GitUser(3, "url3", "name3", "avatarUrl3")
        )
    }
    override suspend fun fetchUsers(): List<GitUser> {
        var users: List<GitUser> = database.fetchUsers()
        if (users.isEmpty()) {
            users = network.fetchUsers()
            database.save(users)
        }
        return users
    }
}