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
    private lateinit var textViewSleptIn: TextView
    private var sleptInStarted = false
    private var sleptInStartTime: Long = 0
    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            if (isNight) {
                val currentTime = System.currentTimeMillis()
                val displayTime = currentTime - startTime
                val s = displayTime / 1000
                val m = s / 60
                val h = m / 60
                val result = "Time Asleep: $h hours, ${m % 60} minutes, ${s % 60} seconds"
                textViewResult.text = result

                if (displayTime >= 10000 && !sleptInStarted) {
                    sleptInStarted = true
                    sleptInStartTime = currentTime
                }

                if (sleptInStarted) {
                    val sleptInTime = currentTime - sleptInStartTime
                    val sleptInS = sleptInTime / 1000
                    val sleptInM = sleptInS / 60
                    val sleptInH = sleptInM / 60
                    val sleptInResult = "$sleptInH hours, ${sleptInM % 60} minutes, ${sleptInS % 60} seconds"
                    textViewSleptIn.text = sleptInResult
                }

                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sleep)

        textViewResult = findViewById(R.id.textView11)
        textViewSleptIn = findViewById(R.id.textView10)
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
        sleptInStarted = false
        handler.post(runnable)
    }

    private fun stopTimer() {
        if (isNight) {
            handler.removeCallbacks(runnable)
            isNight = false
        }
    }
}
