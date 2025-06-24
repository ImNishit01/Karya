package com.example.karya.models

data class Category(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val icon: String = "",
    val image: String = "",
    val subcategories: List<Subcategory> = emptyList(),
    val isActive: Boolean = true,
    val order: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)

data class Subcategory(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val icon: String = "",
    val isActive: Boolean = true,
    val order: Int = 0
) 