package com.android.leanlife

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.leanlife.databinding.ActivityHomeBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    lateinit var kaloriSisa : PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            kaloriSisa = chartKaloriSisa


        }

        val list : ArrayList<PieEntry> = arrayListOf()

        list.add(PieEntry(100f,""))
        list.add(PieEntry(101f,""))

        val pieDataSet = PieDataSet(list,"")
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        pieDataSet.valueTextSize = 0f

        val pieData = PieData(pieDataSet)

        kaloriSisa.data = pieData
        kaloriSisa.description.text = ""
        kaloriSisa.centerText = "Kalori Sisa"
        kaloriSisa.animateY(2000)
    }
}