package com.marneux.gimenotechtest.data.repository

import com.marneux.gimenotechtest.data.local.db.DataStoreManager
import com.marneux.gimenotechtest.domain.model.User
import com.marneux.gimenotechtest.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dataStoreManager: DataStoreManager) :
    UserRepository {
    // Método para guardar el usuario en DataStore
    override suspend fun saveUser(user: User) {
        dataStoreManager.saveUser(user.email, user.password)
    }

    // Método para obtener el usuario de DataStore
    override fun getUser(): Flow<User?> {
        return dataStoreManager.user
    }

    // Método para borrar el usuario de DataStore
    override suspend fun deleteUser() {
        dataStoreManager.clearUser()
    }

    // Método para comprobar si hay un usuario almacenado en DataStore
    override suspend fun hasUser(): Boolean {
        return dataStoreManager.user.first() != null
    }
}