package com.marneux.gimenotechtest.domain.usecases

import com.marneux.gimenotechtest.domain.model.Employee
import com.marneux.gimenotechtest.domain.repository.EmployeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case para obtener un empleado por su ID.
 *
 * @property repository Repositorio de empleados para acceder a los datos de los empleados.
 */
class GetEmployeeByIdUseCase @Inject constructor(
    private val repository: EmployeeRepository
) {

    /**
     * Ejecuta el caso de uso para obtener un empleado por su ID.
     *
     * @param id ID del empleado a buscar.
     * @return Un Flow que emite el empleado encontrado o null si no existe.
     */
    fun execute(id: Int): Flow<Employee?> {
        return repository.getEmployees().map { employees ->
            employees.find { it.id == id }
        }
    }
}
