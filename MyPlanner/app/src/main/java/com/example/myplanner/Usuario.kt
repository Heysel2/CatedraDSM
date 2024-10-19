package com.example.myplanner

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.Calendar

class Usuario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usuario, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textUser = view.findViewById<TextView>(R.id.textUser)

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user != null) {
            val userEmail = user.email
            textUser.text = userEmail
        }

        val cerrarSesion = view.findViewById<Button>(R.id.logout)

        cerrarSesion.setOnClickListener {
            auth.signOut()

            val context = requireContext()
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)

        }

    }
}