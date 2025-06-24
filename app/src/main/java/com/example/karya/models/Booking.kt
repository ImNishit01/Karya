package com.example.karya.models

data class Booking(
    val id: String = "",
    val customerId: String = "",
    val providerId: String = "",
    val serviceId: String = "",
    val status: BookingStatus = BookingStatus.PENDING,
    val scheduledDate: Long = 0L,
    val scheduledTime: String = "", // Format: "14:30"
    val duration: Int = 0, // in minutes
    val totalAmount: Double = 0.0,
    val address: Address = Address(),
    val notes: String = "",
    val customerRating: Double = 0.0,
    val customerReview: String = "",
    val providerRating: Double = 0.0,
    val providerReview: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class BookingStatus {
    PENDING,
    CONFIRMED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED,
    REJECTED
} 