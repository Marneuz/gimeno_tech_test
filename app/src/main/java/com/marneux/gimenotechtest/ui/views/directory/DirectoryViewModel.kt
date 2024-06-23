package com.marneux.gimenotechtest.ui.views.directory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marneux.gimenotechtest.domain.model.Employee
import com.marneux.gimenotechtest.domain.usecases.DeleteUserUseCase
import com.marneux.gimenotechtest.domain.usecases.GetEmployeesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DirectoryViewModel @Inject constructor(
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    // Estado para la consulta de búsqueda
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    // Estado para los empleados agrupados
    private val _groupedEmployees = MutableStateFlow<Map<Char, List<Employee>>>(emptyMap())
    val groupedEmployees: StateFlow<Map<Char, List<Employee>>> get() = _groupedEmployees

    init {
        // Cargar los empleados al inicializar el ViewModel
        loadEmployees()
    }

    private fun loadEmployees() {
        viewModelScope.launch {
            // Combina el flujo de empleados con el flujo de la consulta de búsqueda
            getEmployeesUseCase.execute().combine(_searchQuery) { employees, query ->
                if (query.isEmpty()) {
                    // Si la consulta está vacía, devuelve todos los empleados
                    employees
                } else {
                    // Filtra los empleados según la consulta de búsqueda
                    employees.mapValues { entry ->
                        entry.value.filter { employee ->
                            employee.name.contains(query, ignoreCase = true) ||
                                    employee.lastName.contains(query, ignoreCase = true) ||
                                    employee.position.contains(query, ignoreCase = true)
                        }
                    }.filter { it.value.isNotEmpty() }
                }
            }.collect { groupedEmployees ->
                // Actualiza el estado de los empleados agrupados
                _groupedEmployees.value = groupedEmployees
            }
        }
    }

    // Método para cerrar sesión y eliminar al usuario
    fun logout() {
        viewModelScope.launch {
            deleteUserUseCase()
        }
    }

    // Método para actualizar la consulta de búsqueda
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}
