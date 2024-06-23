package com.marneux.gimenotechtest.ui.views.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marneux.gimenotechtest.domain.model.Employee
import com.marneux.gimenotechtest.domain.usecases.GetEmployeeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getEmployeeByIdUseCase: GetEmployeeByIdUseCase
) : ViewModel() {

    // Estado mutable para almacenar los detalles del empleado
    private val _employee = MutableStateFlow<Employee?>(null)
    // Estado inmutable expuesto para acceder a los detalles del empleado
    val employee: StateFlow<Employee?> get() = _employee

    // Función para cargar los detalles de un empleado por su ID
    fun loadEmployeeById(id: Int) {
        viewModelScope.launch {
            // Ejecuta el caso de uso para obtener los detalles del empleado y actualiza el estado
            getEmployeeByIdUseCase.execute(id).collect { employee ->
                _employee.value = employee
            }
        }
    }
}
