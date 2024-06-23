package com.marneux.gimenotechtest.ui.views.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marneux.gimenotechtest.domain.usecases.CheckUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkUserUseCase: CheckUserUseCase
) : ViewModel() {

    // Estado interno mutable que indica si hay un usuario registrado
    private val _hasUser = MutableStateFlow(false)
    // Estado expuesto como flujo inmutable
    val hasUser: StateFlow<Boolean> get() = _hasUser

    // Inicializa el ViewModel y verifica si hay un usuario registrado
    init {
        checkUser()
    }

    // Función que verifica si hay un usuario registrado utilizando el caso de uso `checkUserUseCase`
    private fun checkUser() {
        viewModelScope.launch {
            _hasUser.value = checkUserUseCase()
        }
    }
}
