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

    private val _hasUser = MutableStateFlow(false)
    val hasUser: StateFlow<Boolean> get() = _hasUser

    init {
        checkUser()
    }

    private fun checkUser() {
        viewModelScope.launch {
            _hasUser.value = checkUserUseCase()
        }
    }
}