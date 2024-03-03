package com.example.snaphealth

import android.content.Intent.getIntent
import androidx.activity.ComponentActivity
import kotlin.math.roundToInt
import kotlin.random.Random
import android.os.Bundle


class Recommendation_system : ComponentActivity(){

        // Calculate BMR base on weight, height, age,and gender.
        //https://www.ncbi.nlm.nih.gov/pmc/articles/PMC7784146/#:~:text=In%20men%2C%20the%20Harris%2DBenedict,4.6756%20x%20age%20in%20years
        fun BMR_calculate(
            age: Int,
            height: Double,
            weight: Double,
            gender: Boolean
        ): Double {
            return if (gender) {
                66.4730 + (13.7516 * weight) + (5.0033 * height) - (6.7550 * age)
            } else {
                65.0955 + (9.5634 * weight) + (1.8496 * height) - (4.6756 * age)
            }
        }

        //Calculate tdee base on activity level to know how much calories needed to maintain bodyweight
        //https://www.livestrong.com/article/526442-the-activity-factor-for-calculating-calories-burned/
        fun tdee(bmr: Double, activity_level: String): Double {
            return when (activity_level) {
                "Sedentary" -> bmr * 1.2
                "Lightly" -> bmr * 1.375
                "Moderately" -> bmr * 1.5
                "Very" -> bmr * 1.725f
                else -> bmr * 1.9
            }
        }

        //calculate calories intake up to user's purpose (lose/gain/maintain weight)
        fun calories_intake(
            tdee: Double,
            goal: String,
        ): Double {
            return when (goal) {
                "Lose Weight" -> tdee - 200
                "Gain Weight" -> tdee + 200
                else -> tdee
            }
        }
}