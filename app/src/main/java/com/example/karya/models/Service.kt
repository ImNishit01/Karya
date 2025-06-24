package com.example.karya.models

data class Service(
    val id: String = "",
    val providerId: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val subcategory: String = "",
    val price: Double = 0.0,
    val priceType: PriceType = PriceType.FIXED,
    val images: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val location: Address = Address(),
    val availability: List<Availability> = emptyList(),
    val rating: Double = 0.0,
    val totalReviews: Int = 0,
    val isActive: Boolean = true,
    val isFeatured: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class PriceType {
    FIXED,
    HOURLY,
    DAILY,
    NEGOTIABLE
}

data class Availability(
    val dayOfWeek: Int = 0, // 0 = Sunday, 1 = Monday, etc.
    val startTime: String = "", // Format: "09:00"
    val endTime: String = "", // Format: "17:00"
    val isAvailable: Boolean = true
) 