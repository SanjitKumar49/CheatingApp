package com.sanjit49.cheatingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupPage : AppCompatActivity() {
   private lateinit var edtName2: EditText
    private lateinit var edtemail2: EditText
    private lateinit var edtpassword2: EditText
    private lateinit var btnSignup2: Button

    private lateinit var mAuth: FirebaseAuth  //for authentication
    private  lateinit var mDbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)
          // initialize the firebase db
        mAuth=FirebaseAuth.getInstance()


       edtName2=findViewById(R.id.edt_name2)
        edtemail2=findViewById(R.id.edt_email2)
        edtpassword2=findViewById(R.id.edt_password2)
        btnSignup2=findViewById(R.id.btn_signup2)

        btnSignup2.setOnClickListener {
           val name2=edtName2.text.toString()
            val email2=edtemail2.text.toString()
            val password2=edtpassword2.text.toString()
            signup(name2,email2,password2)
           // addUserToDatabase(name2,email2,mAuth.currentUser?.uid!!)
        }
    }
    private fun signup( name:String,email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                     addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "error message", Toast.LENGTH_LONG).show()
                }
            }
    }



    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDbref=FirebaseDatabase.getInstance().reference
        mDbref.child("user").child(uid).setValue(User(name,email,uid))

    }
}