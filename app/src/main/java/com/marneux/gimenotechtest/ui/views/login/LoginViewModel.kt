package com.marneux.gimenotechtest.ui.views.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marneux.gimenotechtest.domain.model.User
import com.marneux.gimenotechtest.domain.usecases.LoginUseCase
import com.marneux.gimenotechtest.ui.views.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    // Estado mutable para almacenar el estado actual del login
    private val _loginState = MutableStateFlow<ViewState<User>>(ViewState.Idle)
    val loginState: StateFlow<ViewState<User>> get() = _loginState

    // Función para manejar el proceso de login
    fun login(email: String, password: String) {
        viewModelScope.launch {
            // Actualiza el estado a 'Loading' mientras se procesa el login
            _loginState.value = ViewState.Loading
            // Llama al caso de uso de login y obtiene el resultado
            val result = loginUseCase(email, password)
            // Actualiza el estado según el resultado del caso de uso
            _loginState.value = if (result.isSuccess) {
                ViewState.Success(result.getOrNull()!!)
            } else {
                ViewState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
            }
        }
    }
}
