package com.marneux.gimenotechtest.ui.views.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marneux.gimenotechtest.domain.model.User
import com.marneux.gimenotechtest.domain.usecases.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> get() = _registerState

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            val result = registerUserUseCase(email, password)
            _registerState.value = if (result.isSuccess) {
                RegisterState.Success(result.getOrNull()!!)
            } else {
                RegisterState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
            }
        }
    }
}

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val user: User) : RegisterState()
    data class Error(val message: String) : RegisterState()
}
