package com.marneux.gimenotechtest.domain.usecases

import com.marneux.gimenotechtest.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Use case para verificar si hay un usuario registrado.
 *
 * @property userRepository Repositorio de usuario para acceder a los datos del usuario.
 */
class CheckUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    /**
     * Ejecuta el caso de uso para comprobar si hay un usuario registrado.
     *
     * @return Boolean indicando si hay un usuario registrado.
     */
    suspend operator fun invoke(): Boolean {
        return userRepository.hasUser()
    }
}