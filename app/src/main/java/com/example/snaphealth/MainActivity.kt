package com.example.snaphealth

import android.annotation.SuppressLint
import android.app.Instrumentation
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.content.SharedPreferences
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.LocalDate.now


class MainActivity :  ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switchButton = findViewById<Button>(R.id.button)
        switchButton.setOnClickListener {
            val intent = Intent(this, Hydration::class.java)
            startActivity(intent)
        }
    }
}

