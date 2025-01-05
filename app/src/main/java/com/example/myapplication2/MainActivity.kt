package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Adjust the layout padding based on the system window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Load the fade-in animation
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.animator.fade_in)

        // Reference to the "Amore Mi'o" TextView
        val textViewAmoreMio = findViewById<TextView>(R.id.textViewAmoreMio)
        textViewAmoreMio.startAnimation(fadeInAnimation)

        // Reference to "The home of Italian food" TextView
        val textViewHeader = findViewById<TextView>(R.id.textViewHeader)
        textViewHeader.startAnimation(fadeInAnimation)

        // Listener for the menu button
        findViewById<Button>(R.id.buttonMenu).setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        // Listener for the reservation button
        findViewById<Button>(R.id.buttonReservation).setOnClickListener {
            val intent = Intent(this, ReservationActivity::class.java)
            startActivity(intent)
        }
    }
}
