package com.example.noteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.R
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    // Firebase Authentication instance for managing user authentication
    private lateinit var auth: FirebaseAuth

    // Called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initializing Firebase Authentication instance
        auth = FirebaseAuth.getInstance()

        // Getting references to the UI elements (input fields and buttons)
        val emailField = findViewById<EditText>(R.id.emailField)
        val phoneField = findViewById<EditText>(R.id.phoneField)
        val nameField = findViewById<EditText>(R.id.nameField)
        val passwordField = findViewById<EditText>(R.id.passwordField)
        val confirmPasswordField = findViewById<EditText>(R.id.confirmPasswordField)
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        val loginButton = findViewById<TextView>(R.id.loginButton)

        // SignUp button listener
        signUpButton.setOnClickListener {
            // Getting the values entered by the user in the input fields
            val email = emailField.text.toString().trim()
            val phone = phoneField.text.toString().trim()
            val name = nameField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            val confirmPassword = confirmPasswordField.text.toString().trim()

            // Checking if all fields are filled
            if (email.isNotEmpty() && phone.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                // Checking if password and confirm password match
                if (password == confirmPassword) {
                    // Creating a new user with email and password using Firebase Authentication
                    //paste here sign up logic

                } else {
                    // Display message if passwords do not match
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Display message if not all fields are filled
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Login button listener (redirects to LoginActivity)
        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
