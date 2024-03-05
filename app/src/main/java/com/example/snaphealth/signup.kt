package com.example.snaphealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import android.util.Log


class SignupPage : ComponentActivity() {
    var genderdefault = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val firstNameEditText = findViewById<EditText>(R.id.firstName)
        val lastNameEditText = findViewById<EditText>(R.id.lastName)
        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val genderGroup = findViewById<RadioGroup>(R.id.genderGroup)
        val ageEditText = findViewById<EditText>(R.id.age)
        val heightEditText = findViewById<EditText>(R.id.height)
        val weightEditText = findViewById<EditText>(R.id.weight)
        val submitButton = findViewById<Button>(R.id.submit)


        genderGroup.setOnCheckedChangeListener { _, id ->
            if (id == R.id.male) {
                genderdefault = true
            } else if (id == R.id.female) {
                genderdefault = false
            }
        }

        //Problem here
        //I want to pass datas -> FoodRecommendation but switch to MainActivity first
        //This function pass data and switch to FoodRecommendation at the same time
        submitButton.setOnClickListener {
            if(validateForm()){
                val newUser = User(
                    firstName = firstNameEditText.text.toString(),
                    lastName = lastNameEditText.text.toString(),
                    username = usernameEditText.text.toString(),
                    password = passwordEditText.text.toString(), // Consider using secure password storage.
                    age = ageEditText.text.toString().toInt(),
                    height = heightEditText.text.toString().toDouble(),
                    weight = weightEditText.text.toString().toDouble(),
                    gender = genderdefault
                )

                val gson = Gson()
                val userJson = gson.toJson(newUser)

                val filename = "user_data.json"
                openFileOutput(filename, MODE_PRIVATE).use {
                    it.write(userJson.toByteArray())
                }

                val submitIntent = Intent(this, FoodRecommendation::class.java).apply {
                    putExtra("height", newUser.height)
                    putExtra("weight", newUser.weight)
                    putExtra("age", newUser.age)
                    putExtra("gender", newUser.gender)
                }

                readAndLogUserData()

                //firstname, lastname, username, and password don't need to be passed -> FoodRecommendation
//                val submitIntent = Intent(this, FoodRecommendation::class.java).apply{
//                    putExtra("height", heightEditText.text.toString().toDouble())
//                    putExtra("weight", weightEditText.text.toString().toDouble())
//                    putExtra("age", ageEditText.text.toString().toInt())
//                    putExtra("gender", genderdefault)
//                }
                finish()
//                startActivity(submitIntent)
            }
        }
    }

    //ensure user fill in every blanks
    private fun validateForm(vararg fields: Any): Boolean {
        var isValid = true
        for (field in fields) {
            when (field) {
                is EditText -> {
                    if (field.text.toString().trim().isEmpty()) {
                        field.error = "This field is required"
                        isValid = false
                    }
                }
                is RadioGroup -> {
                    if (field.checkedRadioButtonId == -1) {
                        // Assuming you have a default error message TextView for the RadioGroup
                        val errorMessage = findViewById<TextView>(R.id.gender_error_message)
                        errorMessage.text = "Please select a gender"
                        isValid = false
                    }
                }
            }
        }
        return isValid
    }

    private fun readAndLogUserData() {
        val filename = "user_data.json"
        try {
            val fileInputStream = openFileInput(filename)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var text: String?
            while (run { text = bufferedReader.readLine(); text } != null) {
                stringBuilder.append(text)
            }
            Log.d("SignupPage", "User data JSON: ${stringBuilder.toString()}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

