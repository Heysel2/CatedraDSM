package com.example.myplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var btnLogin : Button
    private lateinit var textViewRegistrarse: TextView
    private lateinit var authStateListener:  FirebaseAuth.AuthStateListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        btnLogin = findViewById<Button>(R.id.buttonIniciar)
        btnLogin.setOnClickListener{
            val email = findViewById<EditText>(R.id.Email).text.toString()
            val password = findViewById<EditText>(R.id.Password).text.toString()
            this.login(email, password)
        }

        textViewRegistrarse = findViewById<TextView>(R.id.textRegistrarse)
        textViewRegistrarse.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        this.checkUser()

    }

    override fun onResume(){
        super.onResume()
        auth.addAuthStateListener(authStateListener)
    }

    override fun onPause(){
        super.onPause()
        auth.removeAuthStateListener(authStateListener)
    }

    private fun checkUser(){
        authStateListener = FirebaseAuth.AuthStateListener{ auth ->
            if(auth.currentUser != null){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun login(email: String, password: String){

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{task ->
            if (task.isSuccessful){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun goToRegister(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}