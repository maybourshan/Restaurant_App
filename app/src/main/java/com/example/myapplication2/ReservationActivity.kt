package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reservation)

        // Initialize the spinners
        val mainSpinnerLayout = R.layout.simple_spinner_item
        val dropdownSpinnerLayout = R.layout.simple_spinner_dropdown_item

        val spinnerSeats = findViewById<Spinner>(R.id.spinnerSeats).apply {
            adapter = ArrayAdapter.createFromResource(
                this@ReservationActivity, R.array.number_of_seats_array, mainSpinnerLayout
            ).apply {
                setDropDownViewResource(dropdownSpinnerLayout)
            }
        }

        val spinnerMonth = findViewById<Spinner>(R.id.spinnerMonth).apply {
            adapter = ArrayAdapter.createFromResource(
                this@ReservationActivity, R.array.months_array, mainSpinnerLayout
            ).apply {
                setDropDownViewResource(dropdownSpinnerLayout)
            }
        }

        val spinnerDay = findViewById<Spinner>(R.id.spinnerDay).apply {
            adapter = ArrayAdapter.createFromResource(
                this@ReservationActivity, R.array.days_array, mainSpinnerLayout
            ).apply {
                setDropDownViewResource(dropdownSpinnerLayout)
            }
        }

        val spinnerHour = findViewById<Spinner>(R.id.spinnerHour).apply {
            adapter = ArrayAdapter.createFromResource(
                this@ReservationActivity, R.array.hours_array, mainSpinnerLayout
            ).apply {
                setDropDownViewResource(dropdownSpinnerLayout)
            }
        }

        val spinnerMinute = findViewById<Spinner>(R.id.spinnerMinute).apply {
            adapter = ArrayAdapter.createFromResource(
                this@ReservationActivity, R.array.minutes_array, mainSpinnerLayout
            ).apply {
                setDropDownViewResource(dropdownSpinnerLayout)
            }
        }

        // Back button navigation
        val buttonBack = findViewById<Button>(R.id.btnBack)
        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // Reserve Seats button navigation
        val buttonReserveSeats = findViewById<Button>(R.id.btnReserveSeats)
        buttonReserveSeats.setOnClickListener {
            // Retrieve user inputs
            val firstName = findViewById<EditText>(R.id.etFirstName).text.toString()
            val lastName = findViewById<EditText>(R.id.etLastName).text.toString()
            val selectedNumber = spinnerSeats.selectedItem?.toString() ?: "0"
            val paymentMethodId = findViewById<RadioGroup>(R.id.rgPaymentMethod).checkedRadioButtonId
            val paymentMethod = if (paymentMethodId != -1) findViewById<RadioButton>(paymentMethodId).text.toString() else "Unknown"
            val veganOptionId = findViewById<RadioGroup>(R.id.rgVegan).checkedRadioButtonId
            val veganOption = if (veganOptionId != -1) findViewById<RadioButton>(veganOptionId).text.toString() else "Unknown"

            // Retrieve the selected values from the spinners
            val month = spinnerMonth.selectedItem?.toString() ?: "Unknown"
            val day = spinnerDay.selectedItem?.toString() ?: "Unknown"
            val hour = spinnerHour.selectedItem?.toString() ?: "00"
            val minute = spinnerMinute.selectedItem?.toString() ?: "00"
            val date = "$day/$month"
            val time = "$hour:$minute"

            // Create an intent for the after reservation page
            val intent = Intent(this, AfterreservationActivity::class.java).apply {
                putExtra("FIRST_NAME", firstName)
                putExtra("LAST_NAME", lastName)
                putExtra("SEATS", selectedNumber)
                putExtra("DATE", date)
                putExtra("TIME", time)
                putExtra("PAYMENT_METHOD", paymentMethod)
                putExtra("VEGAN_OPTION", veganOption)
            }

            // Start the Afterreservation activity
            startActivity(intent)
        }
    }
}

