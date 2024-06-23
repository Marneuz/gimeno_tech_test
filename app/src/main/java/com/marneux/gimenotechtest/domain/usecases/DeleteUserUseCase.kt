package com.marneux.gimenotechtest.domain.usecases

import com.marneux.gimenotechtest.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Use case para eliminar un usuario registrado.
 *
 * @property userRepository Repositorio de usuario para acceder a los datos del usuario.
 */
class DeleteUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    /**
     * Ejecuta el caso de uso para eliminar al usuario registrado.
     */
    suspend operator fun invoke() {
        userRepository.deleteUser()
    }
}
