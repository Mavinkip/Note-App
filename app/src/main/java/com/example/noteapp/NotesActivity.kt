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
                // Save the note to Firestore under the user's
                //paste saving logic here
            }
        }
    }

    // Function to fetch and display the note from Firestore
    private fun fetchNote() {
        val userId = auth.currentUser?.uid // Get the current user ID
        if (userId != null) {
            // Fetch the note from Firestore for the current user
            //paste fetching logic hee

        }
    }
}
