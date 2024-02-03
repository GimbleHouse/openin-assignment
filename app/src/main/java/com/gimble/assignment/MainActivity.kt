package com.gimble.assignment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gimble.assignment.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialSetup()
    }

    private fun initialSetup() {

        navsetter()
}

    private fun navsetter() {
         val nav = binding.bottomNav
        nav.background=null
        binding.coordinatorLayout.setBackgroundColor(Color.TRANSPARENT)

    }



    }