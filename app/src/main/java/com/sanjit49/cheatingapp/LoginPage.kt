package com.sanjit49.cheatingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {
    private lateinit var edtemail:EditText
    private lateinit var edtpassword:EditText
    private lateinit var btnSignup:Button
    private lateinit var btnlogin:Button

    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        supportActionBar?.hide() //Actionbar
        edtemail=findViewById(R.id.edt_email)
        edtpassword=findViewById(R.id.edt_password)
        btnSignup=findViewById(R.id.btn_signup)
        btnlogin=findViewById(R.id.btn_login)

          // initialize the firebase db
        mAuth=FirebaseAuth.getInstance()

        btnSignup.setOnClickListener {
            val intent=Intent(this,SignupPage::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            val email=edtemail.text.toString()
            val password=edtpassword.text.toString()
            login(email,password)
        }
    }

    private  fun login(email:String,password:String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent=Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "user doest'n exist.",Toast.LENGTH_SHORT).show()

                }
            }
    }
}