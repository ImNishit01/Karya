package com.example.karya

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.karya.database.DemoDataGenerator
import com.example.karya.database.FirestoreHelper
import kotlinx.coroutines.launch

class DatabaseDemoActivity : AppCompatActivity() {
    
    private lateinit var firestoreHelper: FirestoreHelper
    private lateinit var demoDataGenerator: DemoDataGenerator
    private lateinit var statusTextView: TextView
    private lateinit var generateDataButton: Button
    private lateinit var loadDataButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_demo)
        
        firestoreHelper = FirestoreHelper()
        demoDataGenerator = DemoDataGenerator(firestoreHelper)
        
        statusTextView = findViewById(R.id.statusTextView)
        generateDataButton = findViewById(R.id.generateDataButton)
        loadDataButton = findViewById(R.id.loadDataButton)
        
        generateDataButton.setOnClickListener {
            generateDemoData()
        }
        
        loadDataButton.setOnClickListener {
            loadAndDisplayData()
        }
    }
    
    private fun generateDemoData() {
        statusTextView.text = "Generating demo data..."
        generateDataButton.isEnabled = false
        
        lifecycleScope.launch {
            try {
                demoDataGenerator.generateDemoData()
                statusTextView.text = "Demo data generated successfully!"
                Log.d("DatabaseDemo", "Demo data generated successfully")
            } catch (e: Exception) {
                statusTextView.text = "Error generating demo data: ${e.message}"
                Log.e("DatabaseDemo", "Error generating demo data", e)
            } finally {
                generateDataButton.isEnabled = true
            }
        }
    }
    
    private fun loadAndDisplayData() {
        statusTextView.text = "Loading data..."
        loadDataButton.isEnabled = false
        
        lifecycleScope.launch {
            try {
                val categories = firestoreHelper.getCategories()
                val services = firestoreHelper.getServices()
                val users = firestoreHelper.getUser("user_customer_1")
                
                val dataSummary = buildString {
                    appendLine("Database Summary:")
                    appendLine("Categories: ${categories.getOrNull()?.size ?: 0}")
                    appendLine("Services: ${services.getOrNull()?.size ?: 0}")
                    appendLine("Sample User: ${users.getOrNull()?.name ?: "Not found"}")
                }
                
                statusTextView.text = dataSummary
                Log.d("DatabaseDemo", "Data loaded successfully")
            } catch (e: Exception) {
                statusTextView.text = "Error loading data: ${e.message}"
                Log.e("DatabaseDemo", "Error loading data", e)
            } finally {
                loadDataButton.isEnabled = true
            }
        }
    }
} 