package com.marneux.gimenotechtest.domain.usecases

import com.marneux.gimenotechtest.domain.model.Employee
import com.marneux.gimenotechtest.domain.repository.EmployeeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case para obtener una lista de empleados agrupados por la inicial de su apellido.
 *
 * @property repository Repositorio de empleados para acceder a los datos de los empleados.
 */
class GetEmployeesUseCase @Inject constructor(
    private val repository: EmployeeRepository
) {

    /**
     * Ejecuta el caso de uso para obtener una lista de empleados agrupados por la inicial de su apellido.
     *
     * @return Un Flow que emite un mapa donde las claves son las iniciales de los apellidos y los valores son listas de empleados.
     */
    fun execute(): Flow<Map<Char, List<Employee>>> {
        return repository.getEmployees().map { employeeList ->
            employeeList.groupBy { it.lastName.first().uppercaseChar() }
        }
    }
}
