package com.marneux.gimenotechtest.ui.views.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marneux.gimenotechtest.domain.model.User
import com.marneux.gimenotechtest.domain.repository.UserRepository
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

    private val _loginState = MutableStateFlow<ViewState<User>>(ViewState.Idle)
    val loginState: StateFlow<ViewState<User>> get() = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = ViewState.Loading
            val result = loginUseCase(email, password)
            _loginState.value = if (result.isSuccess) {
                ViewState.Success(result.getOrNull()!!)
            } else {
                ViewState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
            }
        }
    }
}