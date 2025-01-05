package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AnimationUtils


class AfterreservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.afterreservation)

        // Back button click listener
        val buttonBack = findViewById<Button>(R.id.btnBack)
        buttonBack.setOnClickListener {
            // Navigate to ReservationActivity
            val intent = Intent(this, ReservationActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Confirm button click listener
        findViewById<Button>(R.id.btnConfirm).setOnClickListener {
            // Navigate to SplashActivity
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Retrieve data from the Intent
        val firstName = intent.getStringExtra("FIRST_NAME") ?: getString(R.string.default_value)
        val lastName = intent.getStringExtra("LAST_NAME") ?: getString(R.string.default_value)
        val seats = intent.getStringExtra("SEATS") ?: getString(R.string.default_value)
        val paymentMethod = intent.getStringExtra("PAYMENT_METHOD") ?: getString(R.string.default_value)
        val date = intent.getStringExtra("DATE") ?: getString(R.string.default_value)
        val time = intent.getStringExtra("TIME") ?: getString(R.string.default_value)

        // Retrieve the vegan option key correctly from intent
        val veganOption = intent.getStringExtra("VEGAN_OPTION") ?: getString(R.string.default_value)

        // Set text to TextViews using localized string resources
        findViewById<TextView>(R.id.tvFirstName).text = getString(R.string.tv_first_name, firstName)
        findViewById<TextView>(R.id.tvLastName).text = getString(R.string.tv_last_name, lastName)
        findViewById<TextView>(R.id.tvSeats).text = getString(R.string.tv_seats_reserved, seats)
        findViewById<TextView>(R.id.tvPaymentMethod).text = getString(R.string.tv_payment_method, paymentMethod)
        findViewById<TextView>(R.id.tvVegan).text = getString(R.string.tv_vegan_option, veganOption)
        findViewById<TextView>(R.id.tvDate).text = getString(R.string.tv_date, date)
        findViewById<TextView>(R.id.tvTime).text = getString(R.string.tv_time, time)

        // Vibrate the arrow image
        val imageViewArrow = findViewById<ImageView>(R.id.imageViewArrow)
        val shakeAnimation = AnimationUtils.loadAnimation(this, R.animator.vibrate)
        imageViewArrow.startAnimation(shakeAnimation)
    }
}
