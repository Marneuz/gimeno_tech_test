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

    private val _registerState = MutableStateFlow<ViewState<User>>(ViewState.Idle)
    val registerState: StateFlow<ViewState<User>> get() = _registerState

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = ViewState.Loading
            val result = registerUserUseCase(email, password)
            _registerState.value = if (result.isSuccess) {
                ViewState.Success(result.getOrNull()!!)
            } else {
                ViewState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
            }
        }
    }
}