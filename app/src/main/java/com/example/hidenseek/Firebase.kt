package com.example.hidenseek

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


/*
    - Firebase Datenbank
    - Initialisierung der Datenbank

    !! noch nicht funktionstauglich
 */
fun fire_Storage(){
    // Write a message to the database
    val database = Firebase.database
    val myRef = database.getReference("message")

    myRef.setValue("Hello, World!")

    // Read from the database
    myRef.addValueEventListener(object: ValueEventListener {

        override fun onDataChange(snapshot: DataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            val value = snapshot.getValue<String>()
            Log.d(TAG, "Value is: " + value)
        }

        override fun onCancelled(error: DatabaseError) {
            Log.w(TAG, "Failed to read value.", error.toException())
        }

    })

}


// Datenklasse für die Nutzer, zur Speicherung von Nutzernamen und Email-Adressen
@IgnoreExtraProperties
data class User(val username: String? = null, val email: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

//Hinzufügen der Nutzer
fun writeNewUser(userId: String, name: String, email: String) {
    var database: DatabaseReference = Firebase.database.reference

    val user = User(name, email)

    database.child("users").child(userId).setValue(user)

    database.child("users").child(userId).child("username").setValue(name)


    // Feedback bei der Userdatenerfassung
    database.child("users").child(userId).setValue(user)
        .addOnSuccessListener {
            // Write was successful!
            // ...
        }
        .addOnFailureListener {
            // Write failed
            // ...
        }



}


//Test
val postListener = object : ValueEventListener {
    override fun onDataChange(dataSnapshot: DataSnapshot) {
        // Get Post object and use the values to update the UI
        val post = dataSnapshot.getValue<Post>()
        // ...
    }

    override fun onCancelled(databaseError: DatabaseError) {
        // Getting Post failed, log a message
        Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
    }
}
//postReference.addValueEventListener(postListener)

@IgnoreExtraProperties
data class Post(
    var uid: String? = "",
    var author: String? = "",
    var title: String? = "",
    var body: String? = "",
    var starCount: Int = 0,
    var stars: MutableMap<String, Boolean> = HashMap(),
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "author" to author,
            "title" to title,
            "body" to body,
            "starCount" to starCount,
            "stars" to stars,
        )
    }
}



