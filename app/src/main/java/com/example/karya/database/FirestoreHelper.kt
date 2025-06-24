package com.example.karya.database

import com.example.karya.models.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class FirestoreHelper {
    private val db = FirebaseFirestore.getInstance()
    
    // Collection references
    private val usersCollection = db.collection("users")
    private val servicesCollection = db.collection("services")
    private val bookingsCollection = db.collection("bookings")
    private val reviewsCollection = db.collection("reviews")
    private val categoriesCollection = db.collection("categories")
    
    // User operations
    suspend fun createUser(user: User): Result<String> {
        return try {
            val docRef = usersCollection.add(user).await()
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getUser(userId: String): Result<User?> {
        return try {
            val document = usersCollection.document(userId).get().await()
            if (document.exists()) {
                Result.success(document.toObject(User::class.java))
            } else {
                Result.success(null)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateUser(userId: String, updates: Map<String, Any>): Result<Unit> {
        return try {
            usersCollection.document(userId).update(updates).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    // Service operations
    suspend fun createService(service: Service): Result<String> {
        return try {
            val docRef = servicesCollection.add(service).await()
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getServices(
        category: String? = null,
        providerId: String? = null,
        isActive: Boolean = true,
        limit: Long = 20
    ): Result<List<Service>> {
        return try {
            var query = servicesCollection.whereEqualTo("isActive", isActive)
            
            if (category != null) {
                query = query.whereEqualTo("category", category)
            }
            
            if (providerId != null) {
                query = query.whereEqualTo("providerId", providerId)
            }
            
            val documents = query.limit(limit).get().await()
            val services = documents.mapNotNull { it.toObject(Service::class.java) }
            Result.success(services)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getService(serviceId: String): Result<Service?> {
        return try {
            val document = servicesCollection.document(serviceId).get().await()
            if (document.exists()) {
                Result.success(document.toObject(Service::class.java))
            } else {
                Result.success(null)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    // Booking operations
    suspend fun createBooking(booking: Booking): Result<String> {
        return try {
            val docRef = bookingsCollection.add(booking).await()
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getBookings(
        customerId: String? = null,
        providerId: String? = null,
        status: BookingStatus? = null
    ): Result<List<Booking>> {
        return try {
            var query: Query = bookingsCollection
            
            if (customerId != null) {
                query = query.whereEqualTo("customerId", customerId)
            }
            
            if (providerId != null) {
                query = query.whereEqualTo("providerId", providerId)
            }
            
            if (status != null) {
                query = query.whereEqualTo("status", status)
            }
            
            val documents = query.orderBy("createdAt", Query.Direction.DESCENDING).get().await()
            val bookings = documents.mapNotNull { it.toObject(Booking::class.java) }
            Result.success(bookings)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateBooking(bookingId: String, updates: Map<String, Any>): Result<Unit> {
        return try {
            bookingsCollection.document(bookingId).update(updates).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    // Review operations
    suspend fun createReview(review: Review): Result<String> {
        return try {
            val docRef = reviewsCollection.add(review).await()
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getReviews(reviewedId: String): Result<List<Review>> {
        return try {
            val documents = reviewsCollection
                .whereEqualTo("reviewedId", reviewedId)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()
            val reviews = documents.mapNotNull { it.toObject(Review::class.java) }
            Result.success(reviews)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    // Category operations
    suspend fun createCategory(category: Category): Result<String> {
        return try {
            val docRef = categoriesCollection.add(category).await()
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getCategories(): Result<List<Category>> {
        return try {
            val documents = categoriesCollection
                .whereEqualTo("isActive", true)
                .orderBy("order", Query.Direction.ASCENDING)
                .get()
                .await()
            val categories = documents.mapNotNull { it.toObject(Category::class.java) }
            Result.success(categories)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 