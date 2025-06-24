# Karya Service Marketplace - Database Demo

This document describes the database structure and demo functionality for the Karya service marketplace Android app.

## Database Structure

The app uses Firebase Firestore as the backend database with the following collections:

### 1. Users Collection (`users`)
Stores user information for customers, service providers, and admins.

**Fields:**
- `id`: Unique user identifier
- `email`: User's email address
- `phone`: User's phone number
- `name`: User's full name
- `profileImage`: URL to profile image
- `userType`: Enum (CUSTOMER, PROVIDER, ADMIN)
- `address`: Address object with street, city, state, zipCode, country, latitude, longitude
- `rating`: Average rating (0.0 - 5.0)
- `totalReviews`: Number of reviews received
- `isVerified`: Boolean indicating if user is verified
- `createdAt`: Timestamp when user was created
- `updatedAt`: Timestamp when user was last updated

### 2. Services Collection (`services`)
Stores service offerings from providers.

**Fields:**
- `id`: Unique service identifier
- `providerId`: Reference to the service provider
- `title`: Service title
- `description`: Detailed service description
- `category`: Main service category
- `subcategory`: Service subcategory
- `price`: Service price
- `priceType`: Enum (FIXED, HOURLY, DAILY, NEGOTIABLE)
- `images`: List of image URLs
- `tags`: List of search tags
- `location`: Service location (Address object)
- `availability`: List of availability slots
- `rating`: Average rating
- `totalReviews`: Number of reviews
- `isActive`: Boolean indicating if service is active
- `isFeatured`: Boolean for featured services
- `createdAt`: Creation timestamp
- `updatedAt`: Last update timestamp

### 3. Bookings Collection (`bookings`)
Stores booking information between customers and providers.

**Fields:**
- `id`: Unique booking identifier
- `customerId`: Reference to customer
- `providerId`: Reference to service provider
- `serviceId`: Reference to the service
- `status`: Booking status enum (PENDING, CONFIRMED, IN_PROGRESS, COMPLETED, CANCELLED, REJECTED)
- `scheduledDate`: Scheduled date timestamp
- `scheduledTime`: Scheduled time (format: "HH:MM")
- `duration`: Duration in minutes
- `totalAmount`: Total booking amount
- `address`: Service location
- `notes`: Additional notes
- `customerRating`: Rating given by customer
- `customerReview`: Review from customer
- `providerRating`: Rating given by provider
- `providerReview`: Review from provider
- `createdAt`: Creation timestamp
- `updatedAt`: Last update timestamp

### 4. Reviews Collection (`reviews`)
Stores reviews and ratings.

**Fields:**
- `id`: Unique review identifier
- `bookingId`: Reference to the booking
- `reviewerId`: ID of the person giving review
- `reviewedId`: ID of the person being reviewed
- `serviceId`: Reference to the service
- `rating`: Rating (0.0 - 5.0)
- `comment`: Review comment
- `images`: List of review images
- `isVerified`: Boolean indicating if review is verified
- `createdAt`: Creation timestamp

### 5. Categories Collection (`categories`)
Stores service categories and subcategories.

**Fields:**
- `id`: Unique category identifier
- `name`: Category name
- `description`: Category description
- `icon`: Icon identifier
- `image`: Category image URL
- `subcategories`: List of subcategory objects
- `isActive`: Boolean indicating if category is active
- `order`: Display order
- `createdAt`: Creation timestamp

## Demo Data

The demo includes sample data for:

### Users
- **Customers**: John Doe, Jane Smith
- **Service Providers**: Mike Johnson (Cleaning), Sarah Wilson (Plumbing), Alex Chen (Web Development), Emma Rodriguez (Fitness Training)

### Services
- Professional House Cleaning ($120 fixed)
- Emergency Plumbing Repair ($150/hour)
- Web Development Services ($75/hour)
- Personal Fitness Training ($60 fixed)

### Categories
- Home Services (Cleaning, Plumbing, Electrical, Gardening)
- Technology (Web Development, Mobile Development, IT Support, Data Recovery)
- Health & Wellness (Personal Training, Massage Therapy, Nutrition, Yoga)
- Education (Academic Tutoring, Language Learning, Music Lessons, Test Preparation)

### Bookings
- Sample bookings with different statuses (completed, confirmed, in-progress, pending)
- Reviews and ratings for completed bookings

## How to Use the Demo

1. **Launch the App**: Open the Karya app
2. **Access Database Demo**: Click the "Database Demo" button on the main screen
3. **Generate Demo Data**: Click "Generate Demo Data" to populate the database with sample data
4. **View Data**: Click "Load & Display Data" to see a summary of the generated data

## Database Operations

The `FirestoreHelper` class provides the following operations:

### User Operations
- `createUser(user: User)`: Create a new user
- `getUser(userId: String)`: Retrieve user by ID
- `updateUser(userId: String, updates: Map<String, Any>)`: Update user information

### Service Operations
- `createService(service: Service)`: Create a new service
- `getServices(category, providerId, isActive, limit)`: Get services with filters
- `getService(serviceId: String)`: Retrieve service by ID

### Booking Operations
- `createBooking(booking: Booking)`: Create a new booking
- `getBookings(customerId, providerId, status)`: Get bookings with filters
- `updateBooking(bookingId: String, updates: Map<String, Any>)`: Update booking status

### Review Operations
- `createReview(review: Review)`: Create a new review
- `getReviews(reviewedId: String)`: Get reviews for a user

### Category Operations
- `createCategory(category: Category)`: Create a new category
- `getCategories()`: Get all active categories

## Firebase Configuration

Make sure you have:
1. Added your `google-services.json` file to the app directory
2. Configured Firebase project with Firestore database
3. Set up appropriate security rules for Firestore

## Security Rules Example

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Users can read/write their own data
    match /users/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
    
    // Services are readable by all, writable by providers
    match /services/{serviceId} {
      allow read: if true;
      allow write: if request.auth != null && 
        request.auth.uid == resource.data.providerId;
    }
    
    // Bookings are readable/writable by involved parties
    match /bookings/{bookingId} {
      allow read, write: if request.auth != null && 
        (request.auth.uid == resource.data.customerId || 
         request.auth.uid == resource.data.providerId);
    }
  }
}
```

## Next Steps

1. Implement user authentication with Firebase Auth
2. Add real-time updates using Firestore listeners
3. Implement push notifications for booking updates
4. Add image upload functionality for service images
5. Implement search and filtering functionality
6. Add payment integration
7. Implement chat functionality between customers and providers 