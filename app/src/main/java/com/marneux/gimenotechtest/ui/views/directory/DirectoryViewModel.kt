package com.marneux.gimenotechtest.ui.views.directory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marneux.gimenotechtest.domain.model.Employee
import com.marneux.gimenotechtest.domain.usecases.GetEmployeeByIdUseCase
import com.marneux.gimenotechtest.domain.usecases.GetEmployeesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DirectoryViewModel @Inject constructor(
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val getEmployeeByIdUseCase: GetEmployeeByIdUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    private val _groupedEmployees = MutableStateFlow<Map<Char, List<Employee>>>(emptyMap())
    val groupedEmployees: StateFlow<Map<Char, List<Employee>>> get() = _groupedEmployees

    init {
        loadEmployees()
    }

    private fun loadEmployees() {
        viewModelScope.launch {
            getEmployeesUseCase.execute().combine(_searchQuery) { employees, query ->
                if (query.isEmpty()) {
                    employees
                } else {
                    employees.mapValues { entry ->
                        entry.value.filter { employee ->
                            employee.name.contains(query, ignoreCase = true) ||
                                    employee.lastName.contains(query, ignoreCase = true) ||
                                        employee.position.contains(query, ignoreCase = true)
                        }
                    }.filter { it.value.isNotEmpty() }
                }
            }.collect { groupedEmployees ->
                _groupedEmployees.value = groupedEmployees
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }


    fun getEmployeeById(id: Int): Flow<Employee?> {
        return getEmployeeByIdUseCase.execute(id)
    }
}