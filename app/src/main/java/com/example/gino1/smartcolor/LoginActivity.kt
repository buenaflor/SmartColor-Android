package com.example.gino1.smartcolor

import android.content.Intent
import android.os.Bundle
import android.security.NetworkSecurityPolicy
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

            

        // if user logged in
        if (auth.currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        login_button.setOnClickListener {
            val email = email_editText.text.toString()
            val password = password_editText.text.toString()

            checkText(email, password)

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    println("Logged in")

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    println("Failure")
                    Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun checkText(email: String, password: String) {
        if (email.isEmpty() && password.isEmpty()) {

        }
    }
}