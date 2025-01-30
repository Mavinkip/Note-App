

This is a simple Android Note-taking app that allows users to sign up, log in, and store notes securely using Firebase Authentication and Firestore.

## Features

- User sign-up and login functionality
- Store notes securely in Firebase Firestore
- Retrieve and display saved notes

## Prerequisites

Before you begin, ensure you have the following installed:

- [Android Studio](https://developer.android.com/studio) (latest version)
- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Firebase Account](https://firebase.google.com/)

## Setup

Follow the steps below to set up the project on your local machine:



### 1. Clone the repository

```bash
git clone https://github.com/Mavinkip/Note-App.git
```

## 1 Sign Up 
```kotlin
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
                   //paste here

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
```

```kotlin
auth.createUserWithEmailAndPassword(email, password)  
    .addOnCompleteListener { task ->  
        // Checking if the user creation was successful  
        if (task.isSuccessful) {  
            // Display success message and navigate to the NotesActivity  
            Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show()  
            startActivity(Intent(this, NotesActivity::class.java))  
        } else {  
            // Display error message if user creation failed  
            Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()  
        }  
    }


### 1. Clone the repository
```
## 2.Login Up 
```kotlin
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
        val signUpButton = findViewById<TextView>(R.id.signUpButton)

        // Login button listener
        loginButton.setOnClickListener {
            // Getting the values entered by the user in the input fields
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            // Checking if email and password are provided
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Attempt to sign in the user using Firebase Authentication
               // paste here
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
```
```kotlin
auth.signInWithEmailAndPassword(email, password)  
    .addOnCompleteListener { task ->  
        // Checking if the login was successful  
        if (task.isSuccessful) {  
            // Display success message and navigate to the NotesActivity  
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()  
            startActivity(Intent(this, NotesActivity::class.java))  
        } else {  
            // Display error message if login failed  
            Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()  
        }  
    }
```### 1. Clone the repository
```
## 3 Note Activity 
```kotlin
package com.example.noteapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NotesActivity : AppCompatActivity() {
    // Firebase Authentication instance to manage user authentication
    private lateinit var auth: FirebaseAuth

    // Firebase Firestore instance to interact with the Firestore database
    private lateinit var db: FirebaseFirestore

    // TextView to display the fetched note
    private lateinit var noteTextView: TextView

    // Called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        // Initialize Firebase Authentication and Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Initialize the UI elements (EditText for input, Button for saving, TextView for displaying)
        val noteField = findViewById<EditText>(R.id.noteField)
        val saveButton = findViewById<Button>(R.id.saveButton)
        noteTextView = findViewById(R.id.noteTextView)

        // Automatically fetch and display the note when the activity starts
        fetchNote()

        // Set listener for the save button to save the note to Firestore
        saveButton.setOnClickListener {
            val note = noteField.text.toString().trim() // Get the note text from the EditText
            val userId = auth.currentUser?.uid // Get the current user ID

            // Check if user is authenticated and the note is not empty
            if (userId != null && note.isNotEmpty()) {
                // Save the note to Firestore under the user's ID
               //paste saving logic
            }
        }
    }

    // Function to fetch and display the note from Firestore
    private fun fetchNote() {
        val userId = auth.currentUser?.uid // Get the current user ID
        if (userId != null) {
            // Fetch the note from Firestore for the current user
           
            //paste fetching logic

                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show() // Show error message if fetch fails
                }
        }
    }
}

```


saving
```kotlin
db.collection("notes").document(userId)  
    .set(mapOf("note" to note)) // Store the note with the user's ID  
    .addOnSuccessListener {  
        Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show() // Show success message  
        fetchNote() // Refresh the displayed note  
    }  
    .addOnFailureListener { e ->  
        Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show() // Show error message if save fails  
    }
```
fetching 
```Kotlin
db.collection("notes").document(userId).get()  
    .addOnSuccessListener { document ->  
        // Check if the document exists in Firestore  
        if (document.exists()) {  
            // Get the note from Firestore and display it  
            val note = document.getString("note")  
            noteTextView.text = note ?: "No note found." // Display the note or a message if no note exists  
        } else {  
            // If no note exists, display "No note found."  
            noteTextView.text = "No note found."  
        }  
    }  

```
