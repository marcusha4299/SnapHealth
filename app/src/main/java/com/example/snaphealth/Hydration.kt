package com.example.snaphealth

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity

import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.LocalDate


class Hydration :  ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_hydration)

        //share preferences (save data so when user reopen the data will not reverse to original
        val sharedPreferences = getSharedPreferences("SP_INFO", MODE_PRIVATE)
        val waterCount = sharedPreferences.getString("WATERCOUNT", "2000")
        var editor = sharedPreferences.edit()


        //start code
        val smallButton: Button = findViewById(R.id.small_button)
        val largeButton: Button = findViewById(R.id.large_button)
        val fiveButton: Button = findViewById(R.id.five_button)
        val mugButton: Button = findViewById(R.id.mug_button)
        val waterCount_Text: TextView = findViewById(R.id.WaterCount_textView)
        val backButton: Button = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            // Navigate back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity
        }
        waterCount_Text.text = waterCount

        smallButton.setOnClickListener{
            waterCount_Text.text = (Integer.parseInt(waterCount_Text.text as String) -43).toString()
            editor.putString("WATERCOUNT", waterCount_Text.text as String)
            editor.apply()
            if (Integer.parseInt(waterCount_Text.text as String) < 1) {
                Toast.makeText(this, "Congratulations on staying hydrated", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Keep up the great work", Toast.LENGTH_LONG).show()
                waterCount_Text.text = "2000"
            }
        }

        largeButton.setOnClickListener{
            waterCount_Text.text = (Integer.parseInt(waterCount_Text.text as String) -77).toString()
            editor.putString("WATERCOUNT", waterCount_Text.text as String)
            editor.commit()
            if (Integer.parseInt(waterCount_Text.text as String) < 1) {
                Toast.makeText(this, "Congratulations on staying hydrated", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Keep up the great work", Toast.LENGTH_LONG).show()
                waterCount_Text.text = "2000"
            }
        }

        fiveButton.setOnClickListener{
            waterCount_Text.text = (Integer.parseInt(waterCount_Text.text as String) -157).toString()
            editor.putString("WATERCOUNT", waterCount_Text.text as String)
            editor.commit()
            if (Integer.parseInt(waterCount_Text.text as String) < 1) {
                Toast.makeText(this, "Congratulations on staying hydrated", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Keep up the great work", Toast.LENGTH_LONG).show()
                waterCount_Text.text = "2000"
            }
        }

        mugButton.setOnClickListener{
            waterCount_Text.text = (Integer.parseInt(waterCount_Text.text as String) -240).toString()
            editor.putString("WATERCOUNT", waterCount_Text.text as String)
            editor.commit()
            if (Integer.parseInt(waterCount_Text.text as String) < 1) {
                Toast.makeText(this, "Congratulations on staying hydrated", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Keep up the great work", Toast.LENGTH_LONG).show()
                waterCount_Text.text = "2000"
            }
        }



        //time
        val currentDate = LocalDate.now().toString()
        val date_textView: TextView = findViewById(R.id.date_textView)
        var pastTime: String = sharedPreferences.getString("PASSTIME", "2024-01-28").toString()
        if (pastTime != currentDate) {
            editor.putString("PASTTIME", currentDate)
            editor.putString("WATERCOUNT", "2000")
            editor.commit()
            waterCount_Text.text = "2000"
        }
        date_textView.text = currentDate

        //clear count
        val clearButton: Button = findViewById(R.id.clear_text)
        clearButton.setOnClickListener {
            waterCount_Text.text = "2000"
            editor.putString("WATERCOUNT", "2000")
            editor.commit()
        }
    }
}
