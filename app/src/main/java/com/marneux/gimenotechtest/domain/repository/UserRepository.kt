package com.marneux.gimenotechtest.domain.repository


import com.marneux.gimenotechtest.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUser(user: User)
    fun getUser(): Flow<User?>
}