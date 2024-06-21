package com.marneux.gimenotechtest.domain.usecases

import com.marneux.gimenotechtest.domain.model.User
import com.marneux.gimenotechtest.domain.repository.UserRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        val user = userRepository.getUser().first()
        return if (user != null && user.email == email && user.password == password) {
            Result.success(user)
        } else {
            Result.failure(IllegalArgumentException("Invalid email or password"))
        }
    }
}