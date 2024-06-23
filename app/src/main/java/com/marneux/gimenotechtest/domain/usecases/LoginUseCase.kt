package com.marneux.gimenotechtest.domain.usecases

import com.marneux.gimenotechtest.domain.model.User
import com.marneux.gimenotechtest.domain.repository.UserRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Use case para manejar el inicio de sesión de un usuario.
 *
 * @property userRepository Repositorio de usuarios para acceder a los datos de los usuarios.
 */
class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    /**
     * Ejecuta el caso de uso para autenticar a un usuario.
     *
     * @param email El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return Un objeto Result que contiene el usuario autenticado en caso de éxito,
     *         o una excepción en caso de fallo.
     */
    suspend operator fun invoke(email: String, password: String): Result<User> {
        val user = userRepository.getUser().first()
        return if (user != null && user.email == email && user.password == password) {
            Result.success(user)
        } else {
            Result.failure(IllegalArgumentException("Invalid email or password"))
        }
    }
}
