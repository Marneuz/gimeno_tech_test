package com.marneux.gimenotechtest.domain.usecases

import android.util.Patterns
import com.marneux.gimenotechtest.domain.model.User
import com.marneux.gimenotechtest.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Use case para registrar un nuevo usuario.
 *
 * @property userRepository Repositorio de usuarios para acceder y almacenar los datos de los usuarios.
 */
class RegisterUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    /**
     * Ejecuta el caso de uso para registrar un nuevo usuario.
     *
     * @param email El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return Un objeto Result que contiene el usuario registrado en caso de éxito,
     *         o una excepción en caso de fallo.
     */
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return if (email.isValidEmail() && password.isValidPassword()) {
            userRepository.saveUser(User(email, password))
            Result.success(User(email, password))
        } else {
            Result.failure(IllegalArgumentException("Invalid email or password format"))
        }
    }
}

/**
 * Extensión de función para validar el formato de un correo electrónico.
 *
 * @return true si el correo electrónico es válido, false en caso contrario.
 */
fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

/**
 * Extensión de función para validar el formato de una contraseña.
 *
 * @return true si la contraseña es válida (mínimo 8 caracteres, al menos una letra mayúscula, una letra minúscula, un dígito y un carácter especial), false en caso contrario.
 */
fun String.isValidPassword() = this.length >= 8 && this.any { it.isUpperCase() } &&
        this.any { it.isLowerCase() } && this.any { it.isDigit() } && this.any { !it.isLetterOrDigit() }
