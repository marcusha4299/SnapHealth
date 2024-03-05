package com.example.snaphealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import android.widget.Toast
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import android.widget.EditText

class homepage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val usernameEditText = findViewById<EditText>(R.id.username_input)
        val passwordEditText = findViewById<EditText>(R.id.password_input)
        val loginBtn = findViewById<Button>(R.id.login_btn)
        val signupBtn = findViewById<Button>(R.id.signup_btn)

        signupBtn.setOnClickListener {
            val intent = Intent(this, SignupPage::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            val inputUsername = usernameEditText.text.toString()
            val inputPassword = passwordEditText.text.toString()

            val user = readUserData()
            if (user != null && inputUsername == user.username && inputPassword == user.password) {
                // Login successful
                val intent = Intent(this, MainActivity::class.java) // Change MainActivity to the next activity after login
                startActivity(intent)
                finish()
            } else {
                // Login failed
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readUserData(): User? {
        val filename = "user_data.json"
        return try {
            val fileInputStream = openFileInput(filename)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var text: String?
            while (run { text = bufferedReader.readLine(); text } != null) {
                stringBuilder.append(text)
            }
            val gson = Gson()
            gson.fromJson(stringBuilder.toString(), User::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
