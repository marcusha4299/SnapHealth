package com.example.snaphealth

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class SleepAnalysis : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sleep_analysis)

        val barchart = findViewById<BarChart>(R.id.barChart)

        // Read data from CSV file and populate the list
        val list: ArrayList<BarEntry> = ArrayList()
        try {
            val file = File(filesDir, "sleep_data.csv")
            val reader = BufferedReader(FileReader(file))
            var line: String?
            // Skip the header line
            reader.readLine()
            while (reader.readLine().also { line = it } != null) {
                val tokens = line!!.split(",")
                val sleepTime = tokens[2].toFloat() / 1000  // Convert ms to seconds
                val oversleepTime = tokens[3].toFloat() / 1000  // Convert ms to seconds
                list.add(BarEntry(list.size.toFloat(), floatArrayOf(sleepTime, oversleepTime)))
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val barDataSet = BarDataSet(list, "Sleep Analysis")
        barDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()

        barDataSet.valueTextColor = Color.BLACK

        val barData = BarData(barDataSet)
        barchart.data = barData

        // Optionally, customize the chart appearance
        barchart.setFitBars(true)
        barchart.description.text = "Sleep Analysis"
        barchart.animateY(2000)
        barchart.invalidate()
    }
}