package com.example.karya.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.karya.R

class LoginFragment : Fragment() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonGoToSignup: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        editTextEmail = view.findViewById(R.id.editTextEmail)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        buttonLogin = view.findViewById(R.id.buttonLogin)
        buttonGoToSignup = view.findViewById(R.id.buttonGoToSignup)

        // Set up click listeners
        buttonLogin.setOnClickListener {
            performLogin()
        }

        buttonGoToSignup.setOnClickListener {
            navigateToSignup()
        }
    }

    private fun performLogin() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        // Basic validation
        if (email.isEmpty()) {
            editTextEmail.error = "Email is required"
            return
        }

        if (password.isEmpty()) {
            editTextPassword.error = "Password is required"
            return
        }

        // For demo purposes, accept any non-empty credentials
        // In a real app, you would authenticate with Firebase Auth here
        if (email.isNotEmpty() && password.isNotEmpty()) {
            Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
            navigateToCustomerDashboard()
        } else {
            Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToSignup() {
        try {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        } catch (e: Exception) {
            // Fallback if navigation is not set up
            Toast.makeText(context, "Navigate to signup", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToCustomerDashboard() {
        try {
            findNavController().navigate(R.id.action_loginFragment_to_customerDashboardFragment)
        } catch (e: Exception) {
            // Fallback if navigation is not set up
            Toast.makeText(context, "Navigate to dashboard", Toast.LENGTH_SHORT).show()
        }
    }
} 