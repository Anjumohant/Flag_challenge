package com.example.flags_challenge.ui.time_picker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import com.example.flags_challenge.R
import com.example.flags_challenge.data.model.FlagsQuestion
import java.util.Calendar

class TimePickerActivity : Activity() {

    private lateinit var startButton: Button
    private lateinit var questionList: List<FlagsQuestion>
    private lateinit var  hourPicker: NumberPicker
    private lateinit var  minutePicker: NumberPicker
    private lateinit var  secondPicker: NumberPicker
    private var countdownTimer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_picker)

        // Initialize Views
         hourPicker = findViewById(R.id.hourPicker)
        minutePicker= findViewById(R.id.minutePicker)
         secondPicker = findViewById(R.id.secondPicker)
        val saveButton: Button = findViewById(R.id.saveButton)

        // Get the current time using Calendar
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)  // 24-hour format
        val currentMinute = calendar.get(Calendar.MINUTE)
        val currentSecond = calendar.get(Calendar.SECOND)

        // Set up the NumberPickers
        setupTimePickers(currentHour, currentMinute, currentSecond)


        saveButton.setOnClickListener {
            val hours = hourPicker.value
            val minutes = minutePicker.value
            val seconds = secondPicker.value
            // Calculate scheduled time in milliseconds
            val calendar = Calendar.getInstance()
            val currentTimeMillis = calendar.timeInMillis

            calendar.set(Calendar.HOUR_OF_DAY, hours)
            calendar.set(Calendar.MINUTE, minutes)
            calendar.set(Calendar.SECOND, seconds)

            val scheduledTimeMillis = calendar.timeInMillis

            if (scheduledTimeMillis <= currentTimeMillis) {
                Toast.makeText(this, "Please select a future time", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                Toast.makeText(
                    this,
                    "Challenge time set to $hours:$minutes:$seconds",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // Start the 20-second timer before the scheduled time
            val countdownStartTimeMillis = scheduledTimeMillis - 20000 // 20 seconds before

            // Calculate delay for the timer
            val delayMillis = countdownStartTimeMillis - currentTimeMillis

            if (delayMillis > 0) {
                // Schedule the countdown to start 20 seconds before the challenge start time
                val countdownStartTime = scheduledTimeMillis - 20000
                val delay = countdownStartTime - currentTimeMillis

                // Start the countdown timer
                countdownTimer = object : CountDownTimer(delay, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        // Update the countdown text
                        val secondsRemaining = millisUntilFinished / 1000
                       // countdownText.text = "CHALLENGE WILL START IN ${String.format("%02d:%02d", secondsRemaining / 60, secondsRemaining % 60)}"
                    }

                    override fun onFinish() {
                        // Start a new activity here
                        val intent = Intent(this@TimePickerActivity, Schedule_TimerActivity::class.java)
                        startActivity(intent)
                    }
                }.start()
            } else {
                // Handle the case where the selected time is in the past

                Toast.makeText(
                    this,
                    "Selected time is in the past.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
    private fun setupTimePickers(hour: Int, minute: Int, second: Int) {
        // Set the min and max values for the pickers
       hourPicker.minValue = 0
        hourPicker.maxValue = 23
        hourPicker.value = hour

        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        minutePicker.value = minute

        secondPicker.minValue = 0
        secondPicker.maxValue = 59
        secondPicker.value = second
    }
}
