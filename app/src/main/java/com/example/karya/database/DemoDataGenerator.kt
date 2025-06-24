package com.example.karya.database

import com.example.karya.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DemoDataGenerator(private val firestoreHelper: FirestoreHelper) {
    
    fun generateDemoData() {
        CoroutineScope(Dispatchers.IO).launch {
            generateCategories()
            generateUsers()
            generateServices()
            generateBookings()
            generateReviews()
        }
    }
    
    private suspend fun generateCategories() {
        val categories = listOf(
            Category(
                id = "cat_1",
                name = "Home Services",
                description = "Professional home maintenance and improvement services",
                icon = "home",
                image = "https://example.com/home_services.jpg",
                subcategories = listOf(
                    Subcategory(id = "sub_1", name = "Cleaning", description = "House cleaning services"),
                    Subcategory(id = "sub_2", name = "Plumbing", description = "Plumbing repair and installation"),
                    Subcategory(id = "sub_3", name = "Electrical", description = "Electrical work and repairs"),
                    Subcategory(id = "sub_4", name = "Gardening", description = "Landscaping and garden maintenance")
                ),
                order = 1
            ),
            Category(
                id = "cat_2",
                name = "Technology",
                description = "IT and technology services",
                icon = "computer",
                image = "https://example.com/tech_services.jpg",
                subcategories = listOf(
                    Subcategory(id = "sub_5", name = "Web Development", description = "Website and web application development"),
                    Subcategory(id = "sub_6", name = "Mobile Development", description = "Mobile app development"),
                    Subcategory(id = "sub_7", name = "IT Support", description = "Computer and network support"),
                    Subcategory(id = "sub_8", name = "Data Recovery", description = "Data recovery and backup services")
                ),
                order = 2
            ),
            Category(
                id = "cat_3",
                name = "Health & Wellness",
                description = "Health and wellness services",
                icon = "favorite",
                image = "https://example.com/health_services.jpg",
                subcategories = listOf(
                    Subcategory(id = "sub_9", name = "Personal Training", description = "Personal fitness training"),
                    Subcategory(id = "sub_10", name = "Massage Therapy", description = "Therapeutic massage services"),
                    Subcategory(id = "sub_11", name = "Nutrition", description = "Nutrition consultation and meal planning"),
                    Subcategory(id = "sub_12", name = "Yoga", description = "Yoga and meditation classes")
                ),
                order = 3
            ),
            Category(
                id = "cat_4",
                name = "Education",
                description = "Educational and tutoring services",
                icon = "school",
                image = "https://example.com/education_services.jpg",
                subcategories = listOf(
                    Subcategory(id = "sub_13", name = "Academic Tutoring", description = "Subject-specific tutoring"),
                    Subcategory(id = "sub_14", name = "Language Learning", description = "Foreign language instruction"),
                    Subcategory(id = "sub_15", name = "Music Lessons", description = "Musical instrument instruction"),
                    Subcategory(id = "sub_16", name = "Test Preparation", description = "Exam preparation and coaching")
                ),
                order = 4
            )
        )
        
        categories.forEach { category ->
            firestoreHelper.createCategory(category)
        }
    }
    
    private suspend fun generateUsers() {
        val users = listOf(
            // Customers
            User(
                id = "user_customer_1",
                email = "john.doe@example.com",
                phone = "+1234567890",
                name = "John Doe",
                userType = UserType.CUSTOMER,
                address = Address(
                    street = "123 Main St",
                    city = "New York",
                    state = "NY",
                    zipCode = "10001",
                    country = "USA"
                ),
                isVerified = true
            ),
            User(
                id = "user_customer_2",
                email = "jane.smith@example.com",
                phone = "+1234567891",
                name = "Jane Smith",
                userType = UserType.CUSTOMER,
                address = Address(
                    street = "456 Oak Ave",
                    city = "Los Angeles",
                    state = "CA",
                    zipCode = "90210",
                    country = "USA"
                ),
                isVerified = true
            ),
            
            // Service Providers
            User(
                id = "user_provider_1",
                email = "mike.cleaner@example.com",
                phone = "+1234567892",
                name = "Mike Johnson",
                userType = UserType.PROVIDER,
                address = Address(
                    street = "789 Service Rd",
                    city = "New York",
                    state = "NY",
                    zipCode = "10002",
                    country = "USA"
                ),
                rating = 4.8,
                totalReviews = 45,
                isVerified = true
            ),
            User(
                id = "user_provider_2",
                email = "sarah.plumber@example.com",
                phone = "+1234567893",
                name = "Sarah Wilson",
                userType = UserType.PROVIDER,
                address = Address(
                    street = "321 Fix St",
                    city = "Los Angeles",
                    state = "CA",
                    zipCode = "90211",
                    country = "USA"
                ),
                rating = 4.9,
                totalReviews = 67,
                isVerified = true
            ),
            User(
                id = "user_provider_3",
                email = "alex.developer@example.com",
                phone = "+1234567894",
                name = "Alex Chen",
                userType = UserType.PROVIDER,
                address = Address(
                    street = "654 Tech Blvd",
                    city = "San Francisco",
                    state = "CA",
                    zipCode = "94102",
                    country = "USA"
                ),
                rating = 4.7,
                totalReviews = 23,
                isVerified = true
            ),
            User(
                id = "user_provider_4",
                email = "emma.trainer@example.com",
                phone = "+1234567895",
                name = "Emma Rodriguez",
                userType = UserType.PROVIDER,
                address = Address(
                    street = "987 Fitness Way",
                    city = "Miami",
                    state = "FL",
                    zipCode = "33101",
                    country = "USA"
                ),
                rating = 4.6,
                totalReviews = 34,
                isVerified = true
            )
        )
        
        users.forEach { user ->
            firestoreHelper.createUser(user)
        }
    }
    
    private suspend fun generateServices() {
        val services = listOf(
            Service(
                id = "service_1",
                providerId = "user_provider_1",
                title = "Professional House Cleaning",
                description = "Complete house cleaning service including kitchen, bathrooms, living areas, and bedrooms. Eco-friendly products used.",
                category = "Home Services",
                subcategory = "Cleaning",
                price = 120.0,
                priceType = PriceType.FIXED,
                images = listOf(
                    "https://example.com/cleaning1.jpg",
                    "https://example.com/cleaning2.jpg"
                ),
                tags = listOf("cleaning", "house", "professional", "eco-friendly"),
                location = Address(
                    city = "New York",
                    state = "NY",
                    country = "USA"
                ),
                availability = listOf(
                    Availability(1, "08:00", "18:00", true),
                    Availability(2, "08:00", "18:00", true),
                    Availability(3, "08:00", "18:00", true),
                    Availability(4, "08:00", "18:00", true),
                    Availability(5, "08:00", "18:00", true)
                ),
                rating = 4.8,
                totalReviews = 45,
                isActive = true,
                isFeatured = true
            ),
            Service(
                id = "service_2",
                providerId = "user_provider_2",
                title = "Emergency Plumbing Repair",
                description = "24/7 emergency plumbing services. Fast response time, licensed and insured plumber.",
                category = "Home Services",
                subcategory = "Plumbing",
                price = 150.0,
                priceType = PriceType.HOURLY,
                images = listOf(
                    "https://example.com/plumbing1.jpg",
                    "https://example.com/plumbing2.jpg"
                ),
                tags = listOf("plumbing", "emergency", "repair", "licensed"),
                location = Address(
                    city = "Los Angeles",
                    state = "CA",
                    country = "USA"
                ),
                availability = listOf(
                    Availability(0, "00:00", "23:59", true),
                    Availability(1, "00:00", "23:59", true),
                    Availability(2, "00:00", "23:59", true),
                    Availability(3, "00:00", "23:59", true),
                    Availability(4, "00:00", "23:59", true),
                    Availability(5, "00:00", "23:59", true),
                    Availability(6, "00:00", "23:59", true)
                ),
                rating = 4.9,
                totalReviews = 67,
                isActive = true,
                isFeatured = true
            ),
            Service(
                id = "service_3",
                providerId = "user_provider_3",
                title = "Web Development Services",
                description = "Full-stack web development including frontend, backend, and database design. Modern technologies and responsive design.",
                category = "Technology",
                subcategory = "Web Development",
                price = 75.0,
                priceType = PriceType.HOURLY,
                images = listOf(
                    "https://example.com/webdev1.jpg",
                    "https://example.com/webdev2.jpg"
                ),
                tags = listOf("web development", "full-stack", "responsive", "modern"),
                location = Address(
                    city = "San Francisco",
                    state = "CA",
                    country = "USA"
                ),
                availability = listOf(
                    Availability(1, "09:00", "17:00", true),
                    Availability(2, "09:00", "17:00", true),
                    Availability(3, "09:00", "17:00", true),
                    Availability(4, "09:00", "17:00", true),
                    Availability(5, "09:00", "17:00", true)
                ),
                rating = 4.7,
                totalReviews = 23,
                isActive = true,
                isFeatured = false
            ),
            Service(
                id = "service_4",
                providerId = "user_provider_4",
                title = "Personal Fitness Training",
                description = "Personalized fitness training programs tailored to your goals. In-home or gym sessions available.",
                category = "Health & Wellness",
                subcategory = "Personal Training",
                price = 60.0,
                priceType = PriceType.FIXED,
                images = listOf(
                    "https://example.com/fitness1.jpg",
                    "https://example.com/fitness2.jpg"
                ),
                tags = listOf("fitness", "personal training", "health", "wellness"),
                location = Address(
                    city = "Miami",
                    state = "FL",
                    country = "USA"
                ),
                availability = listOf(
                    Availability(1, "06:00", "20:00", true),
                    Availability(2, "06:00", "20:00", true),
                    Availability(3, "06:00", "20:00", true),
                    Availability(4, "06:00", "20:00", true),
                    Availability(5, "06:00", "20:00", true),
                    Availability(6, "08:00", "16:00", true)
                ),
                rating = 4.6,
                totalReviews = 34,
                isActive = true,
                isFeatured = false
            )
        )
        
        services.forEach { service ->
            firestoreHelper.createService(service)
        }
    }
    
    private suspend fun generateBookings() {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.timeInMillis
        
        val bookings = listOf(
            Booking(
                id = "booking_1",
                customerId = "user_customer_1",
                providerId = "user_provider_1",
                serviceId = "service_1",
                status = BookingStatus.COMPLETED,
                scheduledDate = currentTime - (7 * 24 * 60 * 60 * 1000), // 7 days ago
                scheduledTime = "14:00",
                duration = 180, // 3 hours
                totalAmount = 120.0,
                address = Address(
                    street = "123 Main St",
                    city = "New York",
                    state = "NY",
                    zipCode = "10001",
                    country = "USA"
                ),
                notes = "Please focus on kitchen and bathrooms",
                customerRating = 5.0,
                customerReview = "Excellent service! Very thorough and professional.",
                providerRating = 5.0,
                providerReview = "Great customer, very cooperative."
            ),
            Booking(
                id = "booking_2",
                customerId = "user_customer_2",
                providerId = "user_provider_2",
                serviceId = "service_2",
                status = BookingStatus.CONFIRMED,
                scheduledDate = currentTime + (2 * 24 * 60 * 60 * 1000), // 2 days from now
                scheduledTime = "10:00",
                duration = 120, // 2 hours
                totalAmount = 300.0,
                address = Address(
                    street = "456 Oak Ave",
                    city = "Los Angeles",
                    state = "CA",
                    zipCode = "90210",
                    country = "USA"
                ),
                notes = "Kitchen sink is clogged and leaking"
            ),
            Booking(
                id = "booking_3",
                customerId = "user_customer_1",
                providerId = "user_provider_3",
                serviceId = "service_3",
                status = BookingStatus.IN_PROGRESS,
                scheduledDate = currentTime + (1 * 24 * 60 * 60 * 1000), // 1 day from now
                scheduledTime = "15:00",
                duration = 240, // 4 hours
                totalAmount = 300.0,
                address = Address(
                    street = "123 Main St",
                    city = "New York",
                    state = "NY",
                    zipCode = "10001",
                    country = "USA"
                ),
                notes = "Need e-commerce website development"
            ),
            Booking(
                id = "booking_4",
                customerId = "user_customer_2",
                providerId = "user_provider_4",
                serviceId = "service_4",
                status = BookingStatus.PENDING,
                scheduledDate = currentTime + (3 * 24 * 60 * 60 * 1000), // 3 days from now
                scheduledTime = "08:00",
                duration = 60, // 1 hour
                totalAmount = 60.0,
                address = Address(
                    street = "456 Oak Ave",
                    city = "Los Angeles",
                    state = "CA",
                    zipCode = "90210",
                    country = "USA"
                ),
                notes = "First session, weight loss goal"
            )
        )
        
        bookings.forEach { booking ->
            firestoreHelper.createBooking(booking)
        }
    }
    
    private suspend fun generateReviews() {
        val reviews = listOf(
            Review(
                id = "review_1",
                bookingId = "booking_1",
                reviewerId = "user_customer_1",
                reviewedId = "user_provider_1",
                serviceId = "service_1",
                rating = 5.0,
                comment = "Mike did an amazing job cleaning our house. Very thorough and professional. Will definitely hire again!",
                isVerified = true
            ),
            Review(
                id = "review_2",
                bookingId = "booking_1",
                reviewerId = "user_provider_1",
                reviewedId = "user_customer_1",
                serviceId = "service_1",
                rating = 5.0,
                comment = "Great customer, very cooperative and understanding. House was well-prepared for cleaning.",
                isVerified = true
            )
        )
        
        reviews.forEach { review ->
            firestoreHelper.createReview(review)
        }
    }
} 