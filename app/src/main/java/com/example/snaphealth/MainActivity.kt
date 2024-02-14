package com.example.snaphealth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button


class MainActivity :  ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switchButton = findViewById<Button>(R.id.hydration_switch)
        switchButton.setOnClickListener {
            val intent = Intent(this, Hydration::class.java)
            startActivity(intent)
        }

        val switchButton1 = findViewById<Button>(R.id.food_switch)
        switchButton1.setOnClickListener {
            val intent = Intent(this, FoodRecommendation::class.java)
            startActivity(intent)
        }

        val switchButton2 = findViewById<Button>(R.id.sleep_switch)
        switchButton2.setOnClickListener {
            val intent = Intent(this, Sleep::class.java)
            startActivity(intent)
        }
    }
}

