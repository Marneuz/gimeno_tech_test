package com.marneux.gimenotechtest.data.repository

import com.marneux.gimenotechtest.data.remote.mock.MockEmployeeData
import com.marneux.gimenotechtest.domain.model.Employee
import com.marneux.gimenotechtest.domain.repository.EmployeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor() : EmployeeRepository {

    // Implementación del método getEmployees() que devuelve un Flow de una lista de empleados
    override fun getEmployees(): Flow<List<Employee>> {
        return flow {
            // Obtiene los empleados simulados, ordenados por apellido
            val employees = MockEmployeeData.getMockEmployees().sortedBy { it.lastName }
            // Emite la lista de empleados ordenada
            emit(employees)
        }
    }
}