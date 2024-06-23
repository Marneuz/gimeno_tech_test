package com.marneux.gimenotechtest.data.local.db

import android.content.Context
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.marneux.gimenotechtest.domain.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


// Propiedad de extensión para crear una instancia de DataStore en el contexto
private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        // Claves para almacenar el correo electrónico y la contraseña del usuario en DataStore
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val PASSWORD_KEY = stringPreferencesKey("password")
    }

    // Flow para obtener el usuario desde DataStore
    val user: Flow<User?> = context.dataStore.data
        .catch { exception ->
            // Maneja la excepción y emite preferencias vacías en caso de error
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            // Recupera el correo electrónico y la contraseña de las preferencias
            val email = preferences[EMAIL_KEY]
            val password = preferences[PASSWORD_KEY]
            // Devuelve un objeto User si ambos valores (email y password) no son nulos, de lo contrario, devuelve null
            if (email != null && password != null) {
                User(email, password)
            } else {
                null
            }
        }
    // Función suspendida para guardar el usuario en DataStore
    suspend fun saveUser(email: String, password: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
            preferences[PASSWORD_KEY] = password
        }
    }
    // Función suspendida para borrar el usuario de DataStore
    suspend fun clearUser() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}


