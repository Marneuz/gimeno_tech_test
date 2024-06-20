package com.marneux.gimenotechtest.domain.usecases

import com.marneux.gimenotechtest.domain.model.Employee
import com.marneux.gimenotechtest.domain.repository.EmployeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetEmployeesUseCase @Inject constructor(
    private val repository: EmployeeRepository
) {
    fun execute(): Flow<Map<Char, List<Employee>>> {
        return repository.getEmployees().map { employeeList ->
            employeeList.groupBy { it.lastName.first().uppercaseChar() }
        }
    }
}