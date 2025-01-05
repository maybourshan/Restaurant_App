package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    // Boolean flags to keep track of each image's enlarged state
    private var isImage1Enlarged = false
    private var isImage2Enlarged = false
    private var isImage3Enlarged = false
    private var isImage4Enlarged = false
    private var isImage5Enlarged = false
    private var isImage6Enlarged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        // Set up the back button
        val backButton = findViewById<Button>(R.id.btnBack)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Find and set up the ImageView click listeners for all images
        val imageView1 = findViewById<ImageView>(R.id.imageViewDish)
        val imageView2 = findViewById<ImageView>(R.id.imageViewDish2)
        val imageView3 = findViewById<ImageView>(R.id.imageViewDish3)
        val imageView4 = findViewById<ImageView>(R.id.imageViewDish4)
        val imageView5 = findViewById<ImageView>(R.id.imageViewDish5)
        val imageView6 = findViewById<ImageView>(R.id.imageViewDish6)

        imageView1.setOnClickListener {
            toggleSize(imageView1, isImage1Enlarged)
            isImage1Enlarged = !isImage1Enlarged
        }

        imageView2.setOnClickListener {
            toggleSize(imageView2, isImage2Enlarged)
            isImage2Enlarged = !isImage2Enlarged
        }

        imageView3.setOnClickListener {
            toggleSize(imageView3, isImage3Enlarged)
            isImage3Enlarged = !isImage3Enlarged
        }

        imageView4.setOnClickListener {
            toggleSize(imageView4, isImage4Enlarged)
            isImage4Enlarged = !isImage4Enlarged
        }

        imageView5.setOnClickListener {
            toggleSize(imageView5, isImage5Enlarged)
            isImage5Enlarged = !isImage5Enlarged
        }

        imageView6.setOnClickListener {
            toggleSize(imageView6, isImage6Enlarged)
            isImage6Enlarged = !isImage6Enlarged
        }
    }

    // Function to scale an ImageView up or down
    private fun toggleSize(imageView: ImageView, isEnlarged: Boolean) {
        val scaleFactor = if (isEnlarged) 1.0f else 1.5f
        val duration = if (isEnlarged) 600L else 300L

        val scaleAnimation = ScaleAnimation(
            1.0f, scaleFactor, // From X scale to X scale
            1.0f, scaleFactor, // From Y scale to Y scale
            Animation.RELATIVE_TO_SELF, 0.5f, // Pivot X at 50% (center)
            Animation.RELATIVE_TO_SELF, 0.5f  // Pivot Y at 50% (center)
        )
        scaleAnimation.duration = duration
        scaleAnimation.fillAfter = true // Maintain scaling state after the animation

        imageView.startAnimation(scaleAnimation)
    }
}
