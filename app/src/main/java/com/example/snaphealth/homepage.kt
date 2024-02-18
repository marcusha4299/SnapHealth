package com.example.snaphealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class homepage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val signupBtn = findViewById<Button>(R.id.signup_btn)
        signupBtn.setOnClickListener {
            val intent = Intent(this, SignupPage::class.java)
            startActivity(intent)
        }

        val loginBtn = findViewById<Button>(R.id.login_btn)
        loginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}