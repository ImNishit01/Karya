package com.example.karya.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.karya.DatabaseDemoActivity
import com.example.karya.R

class WelcomeFragment : Fragment() {

    private lateinit var buttonLogin: Button
    private lateinit var buttonDatabaseDemo: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        buttonLogin = view.findViewById(R.id.buttonLogin)
        buttonDatabaseDemo = view.findViewById(R.id.buttonDatabaseDemo)

        // Set up click listeners
        buttonLogin.setOnClickListener {
            navigateToLogin()
        }

        buttonDatabaseDemo.setOnClickListener {
            navigateToDatabaseDemo()
        }
    }

    private fun navigateToLogin() {
        try {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        } catch (e: Exception) {
            // Fallback if navigation is not set up
        }
    }

    private fun navigateToDatabaseDemo() {
        val intent = Intent(requireContext(), DatabaseDemoActivity::class.java)
        startActivity(intent)
    }
} 