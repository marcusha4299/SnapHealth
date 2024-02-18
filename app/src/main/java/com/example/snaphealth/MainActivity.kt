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

        val switchButton = findViewById<Button>(R.id.btnWater)
        switchButton.setOnClickListener {
            val intent = Intent(this, Hydration::class.java)
            startActivity(intent)
        }

        val switchButton1 = findViewById<Button>(R.id.btnFood)
        switchButton1.setOnClickListener {
            val intent = Intent(this, FoodRecommendation::class.java)
            startActivity(intent)
        }

        val switchButton2 = findViewById<Button>(R.id.btnSleep)
        switchButton2.setOnClickListener {
            val intent = Intent(this, Sleep::class.java)
            startActivity(intent)
        }
    }
}

