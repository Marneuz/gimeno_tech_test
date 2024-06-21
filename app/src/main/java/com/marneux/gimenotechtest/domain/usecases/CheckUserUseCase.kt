package com.marneux.gimenotechtest.domain.usecases

import com.marneux.gimenotechtest.domain.repository.UserRepository
import javax.inject.Inject

class CheckUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(): Boolean {
        return userRepository.hasUser()
    }
}