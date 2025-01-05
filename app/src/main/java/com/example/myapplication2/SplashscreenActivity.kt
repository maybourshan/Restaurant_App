package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen) // Adjust this to your layout file name

        // Find the ImageView and apply infinite rotation animation
        val imageView = findViewById<ImageView>(R.id.imageViewmay)
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.animator.rotate_infinite)
        imageView.startAnimation(rotateAnimation)

        // Transition to ThankyouActivity after a 3-second delay
        Handler().postDelayed({
            val intent = Intent(this, ThankyouActivity::class.java)
            startActivity(intent)
            finish() // Close the splash screen
        }, 3000) // 3-second delay
    }
}
