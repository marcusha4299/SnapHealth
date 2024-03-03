package com.example.snaphealth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.math.roundToInt

class FoodRecommendation :  ComponentActivity() {
    //Set up empty string for level and goal spinning option
    private var level_value: String = ""
    private var goal_value: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_food_recommendation)

        //Types of level activity, pass to recommendation algorithm
        val level_spinner = findViewById<Spinner>(R.id.spinner)
        val level = arrayOf("Sedentary(little or no exercise)", "Lightly(1-2 days/week)","Moderately(3-5 days/week)", "Very(6-7 days/week)","Extremely(Professional athlete)")
        val level_arrayAdp = ArrayAdapter(this@FoodRecommendation, android.R.layout.simple_spinner_dropdown_item, level)
        level_spinner.adapter = level_arrayAdp

        //get data from user choice
        level_spinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                level_value = level[position]
            }
            }

        //Types of goal, pass to recommendation algorithm
        val goal_spinner = findViewById<Spinner>(R.id.spinner2)
        val goal = arrayOf("Lose Weight", "Gain Weight","Maintain Weight")
        val goal_arrayAdp = ArrayAdapter(this@FoodRecommendation, android.R.layout.simple_spinner_dropdown_item, goal)
        goal_spinner.adapter = goal_arrayAdp

        //get data from user choice
        goal_spinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                goal_value = goal[position]
            }
        }

        //Types of diets (yet to used)
        val dietType_spinner = findViewById<Spinner>(R.id.spinner3)
        val dietType = arrayOf("Dash", "Keto","Mediterranean", "Paleo", "Vegan", "General diet")
        val dietType_arrayAdp = ArrayAdapter(this@FoodRecommendation, android.R.layout.simple_spinner_dropdown_item, dietType)
        dietType_spinner.adapter = dietType_arrayAdp

        //clickable textView, switch to meal recommendation (yet to used / will be used to display meals per day)
        val click_here = findViewById<TextView>(R.id.textView9)
        click_here.setOnClickListener {
            val intent = Intent(this, MealRecommendation::class.java)
            startActivity(intent)
        }


        //Calculate button and display textView to return the result from algorithm
        val calculate = findViewById<Button>(R.id.button)
        val calory_display = findViewById<TextView>(R.id.textView6)

        //pass objects and method from algorithm from recommendation_system
        calculate.setOnClickListener {
            val height = intent.getDoubleExtra("height", 0.0)
            val weight = intent.getDoubleExtra("weight", 0.0)
            val age = intent.getIntExtra("age", 0)
            val gender = intent.getBooleanExtra("gender", true)


            //pass algorithm from FoodRecommendation activity to display calories_intake
            val recommendationSystem = Recommendation_system()
            val bmr = recommendationSystem.BMR_calculate(age, height, weight, gender)
            val tdee = recommendationSystem.tdee(bmr, level_value.toString())
            val caloriesIntake = recommendationSystem.calories_intake(tdee, goal_value.toString()).roundToInt()
            calory_display.text = caloriesIntake.toString()

            click_here.setOnClickListener{
                val intent = Intent(this, MealRecommendation::class.java)
                intent.putExtra("calories", caloriesIntake)
                startActivity(intent)
            }
        }
    }
}