package com.example.myapplication2

import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThankyouActivity : AppCompatActivity() {
    private lateinit var tvThankYouMessage: TextView
    private lateinit var checkmarkImage: ImageView
    private lateinit var btnBack: Button
    private val handler = Handler(Looper.getMainLooper())
    private var charIndex = 0

    // Retrieve the thank-you message based on the current locale
    private val textToDisplay by lazy {
        getString(R.string.thank_you_message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.thankyou)

        // Initialize the TextView, ImageView, and Button
        tvThankYouMessage = findViewById(R.id.tvThankYouMessage1)
        checkmarkImage = findViewById(R.id.checkmarkImage)
        btnBack = findViewById(R.id.btnBack)

        // Set the back button and checkmark to be initially invisible
        btnBack.alpha = 0f
        checkmarkImage.alpha = 0f // Make the checkmark invisible

        // Start the text animation letter by letter
        animateText(textToDisplay)
    }

    private fun animateText(txt: String) {
        tvThankYouMessage.text = "" // Clear the TextView initially
        charIndex = 0 // Reset the index

        // Runnable to gradually append characters to the text
        val runnable = object : Runnable {
            override fun run() {
                if (charIndex < txt.length) {
                    tvThankYouMessage.text = tvThankYouMessage.text.toString() + txt[charIndex]
                    charIndex++
                    handler.postDelayed(this, 70)
                } else {
                    // Once the text animation finishes, delay before starting the "V" animation
                    handler.postDelayed({
                        startCheckmarkAnimation()
                    }, 500) // Add a brief pause (0.5 seconds)
                }
            }
        }

        // Start the text animation
        handler.post(runnable)
    }

    private fun startCheckmarkAnimation() {
        // Make the checkmark image visible
        checkmarkImage.alpha = 1f

        // Start the AnimatedVectorDrawable (checkmark animation)
        val checkmarkDrawable = checkmarkImage.drawable as? AnimatedVectorDrawable
        checkmarkDrawable?.start()

        // Start the back button animation after the checkmark animation finishes
        handler.postDelayed({
            showBackButton()
        }, 1000) // Adjust this delay to match the checkmark animation duration
    }

    private fun showBackButton() {
        // Slide in from the right
        val slideIn = AnimationUtils.loadAnimation(this, R.animator.slide_in_from_rigth)
        btnBack.startAnimation(slideIn)
        btnBack.alpha = 1f // Make the button visible

        // Set up the click listener to navigate to the main page
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) // Cleanup to prevent memory leaks
    }
}
