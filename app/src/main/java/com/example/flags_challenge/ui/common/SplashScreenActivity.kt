package com.example.flags_challenge.ui.common

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.flags_challenge.R
import com.example.flags_challenge.databinding.ActivitySplashScreenBinding
import com.example.flags_challenge.ui.time_picker.TimePickerActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_splash_screen)

        // Delay for 3 seconds before navigating to the next activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Navigate to TimePicker Activity
            val intent = Intent(this, TimePickerActivity::class.java)
            startActivity(intent)
            finish() // Close the splash screen so it's not accessible via back button
        }, 3000) // 3000ms = 3 seconds
    }
}
