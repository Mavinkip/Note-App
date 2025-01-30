

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
    .addOnFailureListener { e ->  
        Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show() // Show error message if fetch fails  
    }
```