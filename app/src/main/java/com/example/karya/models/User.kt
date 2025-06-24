package com.example.karya.models

data class User(
    val id: String = "",
    val email: String = "",
    val phone: String = "",
    val name: String = "",
    val profileImage: String = "",
    val userType: UserType = UserType.CUSTOMER,
    val address: Address = Address(),
    val rating: Double = 0.0,
    val totalReviews: Int = 0,
    val isVerified: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class UserType {
    CUSTOMER,
    PROVIDER,
    ADMIN
}

data class Address(
    val street: String = "",
    val city: String = "",
    val state: String = "",
    val zipCode: String = "",
    val country: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
) 