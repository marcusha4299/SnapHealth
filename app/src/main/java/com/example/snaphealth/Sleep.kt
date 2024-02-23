package com.example.snaphealth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import android.Manifest
import android.os.CountDownTimer
import android.widget.Switch
import android.widget.TextView

class Sleep :  ComponentActivity() {
    private var isNight = false
    private var startCounting: Long = 0
    private lateinit var textViewResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sleep)

        textViewResult = findViewById<TextView>(R.id.textView11)
        val switchNight = findViewById<Switch>(R.id.switch1)
        switchNight.setOnCheckedChangeListener{_, switch ->
            if(switch){
                clockCount()
            }else{
                stopCount()
            }
        }

    }
    private fun clockCount(){
        isNight = true
        startCounting = System.currentTimeMillis()
    }

    private fun stopCount(){
        if (isNight) {
            val endCounting = System.currentTimeMillis()
            val displayTime = endCounting - startCounting
            val s = displayTime / 1000
            val m = s / 60
            val h = m / 60
            val result = "Time elapsed: $h hours, ${m % 60} minutes, ${s % 60} seconds"
            textViewResult.text = result
            isNight = false
        }
    }
}