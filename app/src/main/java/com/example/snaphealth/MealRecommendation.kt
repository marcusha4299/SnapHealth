package com.example.snaphealth

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.random.Random

class MealRecommendation : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_meal_recommendation)

        val minput = InputStreamReader(assets.open("Diets.csv"))
        val reader = BufferedReader(minput)

        var line : String?
        val meals: MutableList<String> = mutableListOf()

        while (reader.readLine().also { line = it } != null) {
            val row: List<String> = line!!.split(",")
            if (row.size >= 6) { // Ensure the row has enough columns

                meals.add(line!!)
            }
        }
        val targetCalories = intent.getIntExtra("calories", 0)
        val minCalories = targetCalories - 100
        val maxCalories = targetCalories + 100
        meals.shuffle()

        val selectedMeals: MutableList<String> = mutableListOf()
        var totalSelectedCalories = 0.0

        for (meal in meals) {
            if (selectedMeals.size == 3) {
                break
            }

            val row: List<String> = meal.split(",")
            val protein = row[3].toDoubleOrNull() ?: continue
            val carbs = row[4].toDoubleOrNull() ?: continue
            val fat = row[5].toDoubleOrNull() ?: continue
            val totalCalories = (protein * 1 + carbs * 4 + fat * 9)

            if (totalSelectedCalories + totalCalories <= maxCalories) {
                selectedMeals.add(meal)
                totalSelectedCalories += totalCalories
            }
        }
        val txtData = findViewById<TextView>(R.id.textView13)
        val displayData = StringBuilder()

        for (meal in selectedMeals) {
            val row: List<String> = meal.split(",")
            val protein = row[3].toDoubleOrNull() ?: continue
            val carbs = row[4].toDoubleOrNull() ?: continue
            val fat = row[5].toDoubleOrNull() ?: continue
            val totalCalories = (protein  + carbs * 4 + fat * 4)
            displayData.append("${row[1]} - Total Calories: $totalCalories\n")
        }
        txtData.text = displayData.toString()
    }
}
