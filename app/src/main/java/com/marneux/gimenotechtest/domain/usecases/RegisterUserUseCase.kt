package com.marneux.gimenotechtest.domain.usecases

import android.util.Patterns
import com.marneux.gimenotechtest.domain.model.User
import com.marneux.gimenotechtest.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(email: String, password: String): Result<User> {
        return if (email.isValidEmail() && password.isValidPassword()) {
            userRepository.saveUser(User(email, password))
            Result.success(User(email, password))
        } else {
            Result.failure(IllegalArgumentException("Invalid email or password format"))
        }
    }
}

fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()
fun String.isValidPassword() = this.length >= 8 && this.any { it.isUpperCase() } &&
        this.any { it.isLowerCase() } && this.any { it.isDigit() } && this.any { !it.isLetterOrDigit() }
