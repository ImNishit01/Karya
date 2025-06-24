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

class SignupFragment : Fragment() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignup: Button
    private lateinit var buttonGoToLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        editTextName = view.findViewById(R.id.editTextName)
        editTextEmail = view.findViewById(R.id.editTextEmail)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        buttonSignup = view.findViewById(R.id.buttonSignup)
        buttonGoToLogin = view.findViewById(R.id.buttonGoToLogin)

        // Set up click listeners
        buttonSignup.setOnClickListener {
            performSignup()
        }

        buttonGoToLogin.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun performSignup() {
        val name = editTextName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        // Basic validation
        if (name.isEmpty()) {
            editTextName.error = "Name is required"
            return
        }

        if (email.isEmpty()) {
            editTextEmail.error = "Email is required"
            return
        }

        if (password.isEmpty()) {
            editTextPassword.error = "Password is required"
            return
        }

        if (password.length < 6) {
            editTextPassword.error = "Password must be at least 6 characters"
            return
        }

        // For demo purposes, accept any valid input
        // In a real app, you would create user with Firebase Auth here
        Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
        navigateToLogin()
    }

    private fun navigateToLogin() {
        try {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        } catch (e: Exception) {
            // Fallback if navigation is not set up
            Toast.makeText(context, "Navigate to login", Toast.LENGTH_SHORT).show()
        }
    }
} 