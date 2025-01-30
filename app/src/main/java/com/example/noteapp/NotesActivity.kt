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
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var noteTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val noteField = findViewById<EditText>(R.id.noteField)
        val saveButton = findViewById<Button>(R.id.saveButton)
        noteTextView = findViewById(R.id.noteTextView)

        // Automatically fetch and display note when the activity starts
        fetchNote()

        saveButton.setOnClickListener {
            val note = noteField.text.toString().trim()
            val userId = auth.currentUser?.uid

            if (userId != null && note.isNotEmpty()) {
                db.collection("notes").document(userId)
                    .set(mapOf("note" to note))
                    .addOnSuccessListener {
                        Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
                        fetchNote() // Refresh the displayed note
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun fetchNote() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("notes").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val note = document.getString("note")
                        noteTextView.text = note ?: "No note found."
                    } else {
                        noteTextView.text = "No note found."
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
