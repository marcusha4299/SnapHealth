package com.example.snaphealth

import android.os.Bundle
import androidx.activity.ComponentActivity

class MealRecommendation: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_meal_recommendation)
    }
}