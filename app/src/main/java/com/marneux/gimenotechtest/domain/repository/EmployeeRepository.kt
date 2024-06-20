package com.marneux.gimenotechtest.domain.repository

import com.marneux.gimenotechtest.domain.model.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {
    fun getEmployees(): Flow<List<Employee>>
}