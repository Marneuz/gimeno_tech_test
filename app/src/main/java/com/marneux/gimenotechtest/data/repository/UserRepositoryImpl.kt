package com.marneux.gimenotechtest.data.repository

import com.marneux.gimenotechtest.data.local.db.DataStoreManager
import com.marneux.gimenotechtest.domain.model.User
import com.marneux.gimenotechtest.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dataStoreManager: DataStoreManager) :
    UserRepository {
    override suspend fun saveUser(user: User) {
        dataStoreManager.saveUser(user.email, user.password)
    }

    override fun getUser(): Flow<User?> {
        return dataStoreManager.user
    }

    override suspend fun deleteUser() {
        dataStoreManager.clearUser()
    }
    override suspend fun hasUser(): Boolean {
        return dataStoreManager.user.first() != null
    }
}