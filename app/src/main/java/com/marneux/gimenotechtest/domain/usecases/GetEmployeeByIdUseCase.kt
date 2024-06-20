package com.marneux.gimenotechtest.domain.usecases

import com.marneux.gimenotechtest.domain.model.Employee
import com.marneux.gimenotechtest.domain.repository.EmployeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetEmployeeByIdUseCase @Inject constructor(
    private val repository: EmployeeRepository
) {
    fun execute(id: Int): Flow<Employee?> {
        return repository.getEmployees().map { employees ->
            employees.find { it.id == id }
        }
    }
}