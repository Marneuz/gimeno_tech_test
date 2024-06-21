package com.marneux.gimenotechtest.di

import com.marneux.gimenotechtest.domain.repository.EmployeeRepository
import com.marneux.gimenotechtest.domain.repository.UserRepository
import com.marneux.gimenotechtest.domain.usecases.GetEmployeeByIdUseCase
import com.marneux.gimenotechtest.domain.usecases.GetEmployeesUseCase
import com.marneux.gimenotechtest.domain.usecases.LoginUseCase
import com.marneux.gimenotechtest.domain.usecases.RegisterUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(userRepository: UserRepository): LoginUseCase {
        return LoginUseCase(userRepository)
    }

    @Provides
    fun provideRegisterUserUseCase(userRepository: UserRepository): RegisterUserUseCase {
        return RegisterUserUseCase(userRepository)
    }

    @Provides
    fun provideGetEmployeesUseCase(employeeRepository: EmployeeRepository): GetEmployeesUseCase {
        return GetEmployeesUseCase(employeeRepository)
    }

    @Provides
    fun provideGetEmployeeByIdUseCase(employeeRepository: EmployeeRepository):
            GetEmployeeByIdUseCase {
        return GetEmployeeByIdUseCase(employeeRepository)
    }
}