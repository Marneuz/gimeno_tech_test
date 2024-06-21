package com.marneux.gimenotechtest.domain.usecases

import com.marneux.gimenotechtest.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke() {
        userRepository.deleteUser()
    }
}