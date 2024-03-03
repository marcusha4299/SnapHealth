package com.example.snaphealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.os.Handler
import android.widget.Switch
import android.widget.TextView

class Sleep : ComponentActivity() {
    private var isNight = false
    private var startTime: Long = 0
    private lateinit var textViewResult: TextView
    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            if (isNight) {
                val currentTime = System.currentTimeMillis()
                val displayTime = currentTime - startTime
                val s = displayTime / 1000
                val m = s / 60
                val h = m / 60
                val result = "Time elapsed: $h hours, ${m % 60} minutes, ${s % 60} seconds"
                textViewResult.text = result
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sleep)

        textViewResult = findViewById(R.id.textView11)
        val switchNight = findViewById<Switch>(R.id.switch1)
        switchNight.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                startTimer()
            } else {
                stopTimer()
            }
        }
    }

    private fun startTimer() {
        isNight = true
        startTime = System.currentTimeMillis()
        handler.post(runnable)
    }

    private fun stopTimer() {
        if (isNight) {
            handler.removeCallbacks(runnable)
            isNight = false
        }
    }
}
