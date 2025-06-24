package com.example.karya.models

data class Review(
    val id: String = "",
    val bookingId: String = "",
    val reviewerId: String = "",
    val reviewedId: String = "",
    val serviceId: String = "",
    val rating: Double = 0.0,
    val comment: String = "",
    val images: List<String> = emptyList(),
    val isVerified: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) 