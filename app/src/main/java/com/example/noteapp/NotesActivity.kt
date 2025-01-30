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

            }
        }
    }

    // Function to fetch and display the note from Firestore
    private fun fetchNote() {
        val userId = auth.currentUser?.uid // Get the current user ID
        if (userId != null) {
            // Fetch the note from Firestore for the current user
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
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show() // Show error message if fetch fails
                }
        }
    }
}
