package com.example.snaphealth

import androidx.activity.ComponentActivity
import kotlin.random.Random
import kotlin.math.roundToInt

class Recommendation_system{
    // Calculate BMR base on weight, height, age,and gender.
    //https://www.ncbi.nlm.nih.gov/pmc/articles/PMC7784146/#:~:text=In%20men%2C%20the%20Harris%2DBenedict,4.6756%20x%20age%20in%20years
    fun BMR_calculate(age: Int, height: Double, weight: Double, gender: Boolean):Double {
        return if (gender){
            66.4730 + (13.7516 * weight) + (5.0033 * height) - (6.7550 * age)
        }
        else {
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
            "Very" -> bmr * 1.725
            else -> bmr * 1.9
        }
    }

    //calculate calories intake up to user's purpose (lose/gain/maintain weight)
    fun calories_intake(tdee: Double, goal: String, calories_difference: Double): Double {
        return when (goal) {
            "Lose Weight" -> tdee - calories_difference
            "Gain Weight" -> tdee + calories_difference
            else -> tdee
        }
    }
}

fun main(){
    //test case
    val weight = 60.0
    val height = 174.0
    val age = 25
    val gender = true
    val activity_level = "Moderately"
    val goal = "Gain Weight"
    val calories_difference = Random.nextDouble(200.0,400.0)

    val recommendation_system = Recommendation_system()
    val bmr = recommendation_system.BMR_calculate(age, height, weight, gender)
    val tdee = recommendation_system.tdee(bmr, activity_level)
    val calories = recommendation_system.calories_intake(tdee, goal, calories_difference)

    println("BMR: "+bmr.roundToInt())
    println("TDEE: "+tdee.roundToInt())
    println("Caloric Intake: "+calories.roundToInt())
}