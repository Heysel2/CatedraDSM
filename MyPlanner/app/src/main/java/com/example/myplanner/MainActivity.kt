package com.example.myplanner


import Agregar
import androidx.fragment.app.Fragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myplanner.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Inicio())

        binding.bottomNavigationView.setOnItemSelectedListener{

            when(it.itemId) {
                R.id.inicio -> replaceFragment(Inicio())
                R.id.dia -> replaceFragment(Dia())
                R.id.agregar -> replaceFragment(Agregar())
                R.id.calendario -> replaceFragment(Calendario())
                R.id.user -> replaceFragment(Usuario())

                else -> {

                }
            }
true

        }
    }

    private  fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.framel_layout, fragment)
        fragmentTransition.commit()
    }
}