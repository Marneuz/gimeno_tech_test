package com.marneux.gimenotechtest.domain.model

data class Employee(
    val id: Int,
    val name: String,
    val lastName: String,
    val position: String,
    val phoneNumber: String,
    val email: String,
    val imageResId: Int? = null
)