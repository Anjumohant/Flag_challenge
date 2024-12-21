package com.example.flags_challenge.ui.time_picker

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flags_challenge.R
import com.example.flags_challenge.ui.challenge.ChallengeActivity

class Schedule_TimerActivity : AppCompatActivity() {
    private lateinit var timerTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_timer)
        timerTextView = findViewById(R.id.tv_timer)
        startTimer()
    }
    private fun startTimer() {
        // Define a 20-second countdown timer
        object : CountDownTimer(20000, 1000) { // 20000ms (20 seconds) and 1000ms (1 second intervals)
            override fun onTick(millisUntilFinished: Long) {
                // Update the TextView with the remaining time (in seconds)
                val secondsRemaining = millisUntilFinished / 1000
                timerTextView.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                // When the timer finishes, you can do something (e.g., show a message)
                timerTextView.text = "Time's up!"
                val intent = Intent(this@Schedule_TimerActivity, ChallengeActivity::class.java)
                startActivity(intent)
            }
        }.start()
    }
}