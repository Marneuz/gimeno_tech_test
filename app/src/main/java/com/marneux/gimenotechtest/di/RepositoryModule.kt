package com.marneux.gimenotechtest.di

import com.marneux.gimenotechtest.data.repository.EmployeeRepositoryImpl
import com.marneux.gimenotechtest.data.repository.UserRepositoryImpl
import com.marneux.gimenotechtest.domain.repository.EmployeeRepository
import com.marneux.gimenotechtest.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
    @Binds
    @Singleton
    abstract fun bindEmployeeRepository(
        employeeRepositoryImpl: EmployeeRepositoryImpl
    ): EmployeeRepository
}