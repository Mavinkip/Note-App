package com.example.noteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    // Firebase Authentication instance for managing user authentication
    private lateinit var auth: FirebaseAuth

    // Called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initializing Firebase Authentication instance
        auth = FirebaseAuth.getInstance()

        // Getting references to the UI elements (input fields and buttons)
        val emailField = findViewById<EditText>(R.id.emailField)
        val passwordField = findViewById<EditText>(R.id.passwordField)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signUpButton = findViewById<Button>(R.id.signUpButton)

        // Login button listener
        loginButton.setOnClickListener {
            // Getting the values entered by the user in the input fields
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            // Checking if email and password are provided
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Attempt to sign in the user using Firebase Authentication

            } else {
                // Display message if email or password is not entered
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        // Sign up button listener (redirects to SignUpActivity)
        signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
