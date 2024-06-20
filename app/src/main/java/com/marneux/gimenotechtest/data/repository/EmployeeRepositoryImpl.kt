package com.marneux.gimenotechtest.data.repository

import com.marneux.gimenotechtest.data.remote.mock.MockEmployeeData
import com.marneux.gimenotechtest.domain.model.Employee
import com.marneux.gimenotechtest.domain.repository.EmployeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor() : EmployeeRepository {

    override fun getEmployees(): Flow<List<Employee>> {
        return flow {
            val employees = MockEmployeeData.getMockEmployees().sortedBy { it.lastName }
            emit(employees)
        }
    }
}