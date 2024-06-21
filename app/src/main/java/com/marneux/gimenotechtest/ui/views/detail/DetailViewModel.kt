package com.marneux.gimenotechtest.ui.views.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marneux.gimenotechtest.domain.model.Employee
import com.marneux.gimenotechtest.domain.usecases.GetEmployeeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getEmployeeByIdUseCase: GetEmployeeByIdUseCase
) : ViewModel() {

    private val _employee = MutableStateFlow<Employee?>(null)
    val employee: StateFlow<Employee?> get() = _employee

    fun loadEmployeeById(id: Int) {
        viewModelScope.launch {
            getEmployeeByIdUseCase.execute(id).collect { employee ->
                _employee.value = employee
            }
        }
    }
}