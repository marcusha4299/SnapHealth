package com.example.snaphealth

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class SleepAnalysis : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sleep_analysis)

        val combinedChart = findViewById<CombinedChart>(R.id.barChart)
        var totalSleepHours = 0f
        var numEntries = 0

        // Read user sleep data from CSV file and populate the list
        val userSleepList: ArrayList<BarEntry> = ArrayList()
        try {
            val file = File(filesDir, "sleep_data.csv")
            val reader = BufferedReader(FileReader(file))
            var line: String?
            // Skip the header line
            reader.readLine()
            while (reader.readLine().also { line = it } != null) {
                val tokens = line!!.split(",")
                val sleepTime = tokens[2].toFloat() / (1000*60*60)
                totalSleepHours += sleepTime
                numEntries++
                val oversleepTime = tokens[3].toFloat() / (1000*60*60)
                userSleepList.add(BarEntry(userSleepList.size.toFloat(), floatArrayOf(sleepTime)))
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val averageSleepHours = totalSleepHours / numEntries
        val recommendationSleepHours = calculateRecommendedSleepHours()

        val message: String = when {
            averageSleepHours < recommendationSleepHours - 1 -> {
                "Your Sleep Quality is not good overall. Our data indicates that you have not slept enough. Remind that undersleep is the reason for Memory loss, Poor concentration, Immunodeficiency, Increased risk of cardiovascular disease, stroke and Increased risk of depression."
            }
            averageSleepHours > recommendationSleepHours + 1 -> {
                "Your Sleep Quality is not good overall. Our data indicates that you have slept too much. Remind that oversleep is the reason of Obesity, diabetes, heart disease, headaches and sleep disorders."
            }
            else -> {
                "Your sleep is good overall, keep your sleep as it is right now, you will have a good health condition."
            }
        }

        // Create bar chart
        val userSleepDataSet = BarDataSet(userSleepList, "User Sleep Analysis")
        val blueColor = Color.BLUE
        userSleepDataSet.color = blueColor // Set all bars to blue
        userSleepDataSet.valueTextColor = Color.BLACK

        val userSleepData = BarData(userSleepDataSet)

        // Create line chart
        val recommendedEntries = ArrayList<Entry>()
        for (i in userSleepList.indices) {
            recommendedEntries.add(Entry(i.toFloat(), calculateRecommendedSleepHours()))
        }

        val recommendedDataSet = LineDataSet(recommendedEntries, "Recommended Sleep Hours")
        recommendedDataSet.color = Color.RED
        recommendedDataSet.valueTextColor = Color.BLACK
        recommendedDataSet.mode = LineDataSet.Mode.STEPPED

        val recommendedData = LineData(recommendedDataSet)

        //combined chart for bar chart and line chart
        combinedChart.data = CombinedData().apply {
            setData(userSleepData)
            setData(recommendedData)
        }

        // change color chart
        combinedChart.description.isEnabled = false
        combinedChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        combinedChart.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return value.toInt().toString() // Custom formatting for x-axis labels
            }
        }
        combinedChart.invalidate()
        showMessageDialog(message)
    }

    // Function to calculate recommended sleep hours based on age
    //https://www.cdc.gov/sleep/about_sleep/how_much_sleep.html
    private fun calculateRecommendedSleepHours(): Float {
        var recommendedSleepHours = 8f // Default value for adults
        try {
            val jsonFile = File(filesDir, "user_data.json")
            val reader = BufferedReader(FileReader(jsonFile))
            val jsonString = reader.readText()
            reader.close()
            val jsonObject = JSONObject(jsonString)
            val keys = jsonObject.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                val userData = jsonObject.getJSONArray(key)
                val age = userData[4] as Int// Assuming age is stored at index 4
                recommendedSleepHours = when {
                    age >= 65 -> 8f
                    age in 61..64 -> 9f
                    age in 18..60 -> 8f
                    age in 13..17 -> 10f
                    age in 6..12 -> 12f
                    age in 3..5 -> 13f
                    age in 1..2 -> 14f
                    else -> 8f// Default to adult recommendation
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return recommendedSleepHours
    }
    private fun showMessageDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }
}