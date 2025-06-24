package com.example.karya.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.karya.DatabaseDemoActivity
import com.example.karya.R

class CustomerDashboardFragment : Fragment() {

    private lateinit var textViewCustomerDashboard: TextView
    private lateinit var buttonViewServices: Button
    private lateinit var buttonMyBookings: Button
    private lateinit var buttonProfile: Button
    private lateinit var buttonDatabaseDemo: Button
    private lateinit var buttonLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_customer_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        textViewCustomerDashboard = view.findViewById(R.id.textViewCustomerDashboard)
        buttonViewServices = view.findViewById(R.id.buttonViewServices)
        buttonMyBookings = view.findViewById(R.id.buttonMyBookings)
        buttonProfile = view.findViewById(R.id.buttonProfile)
        buttonDatabaseDemo = view.findViewById(R.id.buttonDatabaseDemo)
        buttonLogout = view.findViewById(R.id.buttonLogout)

        // Set up click listeners
        buttonViewServices.setOnClickListener {
            navigateToServices()
        }

        buttonMyBookings.setOnClickListener {
            navigateToMyBookings()
        }

        buttonProfile.setOnClickListener {
            navigateToProfile()
        }

        buttonDatabaseDemo.setOnClickListener {
            navigateToDatabaseDemo()
        }

        buttonLogout.setOnClickListener {
            performLogout()
        }
    }

    private fun navigateToServices() {
        Toast.makeText(context, "Navigate to Services", Toast.LENGTH_SHORT).show()
        // In a real app, you would navigate to a services list fragment
    }

    private fun navigateToMyBookings() {
        Toast.makeText(context, "Navigate to My Bookings", Toast.LENGTH_SHORT).show()
        // In a real app, you would navigate to a bookings list fragment
    }

    private fun navigateToProfile() {
        Toast.makeText(context, "Navigate to Profile", Toast.LENGTH_SHORT).show()
        // In a real app, you would navigate to a profile fragment
    }

    private fun navigateToDatabaseDemo() {
        val intent = Intent(requireContext(), DatabaseDemoActivity::class.java)
        startActivity(intent)
    }

    private fun performLogout() {
        Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
        try {
            findNavController().navigate(R.id.action_customerDashboardFragment_to_loginFragment)
        } catch (e: Exception) {
            // Fallback if navigation is not set up
            Toast.makeText(context, "Navigate to login", Toast.LENGTH_SHORT).show()
        }
    }
} 