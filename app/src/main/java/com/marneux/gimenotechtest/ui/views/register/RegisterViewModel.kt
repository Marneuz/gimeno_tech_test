package com.marneux.gimenotechtest.ui.views.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marneux.gimenotechtest.domain.model.User
import com.marneux.gimenotechtest.domain.usecases.RegisterUserUseCase
import com.marneux.gimenotechtest.ui.views.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    // Estado mutable para almacenar el estado de la vista de registro
    private val _registerState = MutableStateFlow<ViewState<User>>(ViewState.Idle)
    val registerState: StateFlow<ViewState<User>> get() = _registerState

    // Función para registrar un nuevo usuario
    fun register(email: String, password: String) {
        viewModelScope.launch {
            // Actualiza el estado a 'Loading' mientras se procesa el registro
            _registerState.value = ViewState.Loading
            // Llama al caso de uso de registro de usuario y obtiene el resultado
            val result = registerUserUseCase(email, password)
            // Actualiza el estado según el resultado del caso de uso
            _registerState.value = if (result.isSuccess) {
                ViewState.Success(result.getOrNull()!!)
            } else {
                ViewState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
            }
        }
    }
}
