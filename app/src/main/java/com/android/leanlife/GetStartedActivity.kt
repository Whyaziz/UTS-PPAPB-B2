package com.android.leanlife

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.android.leanlife.databinding.ActivityGetStartedBinding

class GetStartedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetStartedBinding

    private val satuanBeratBadan = arrayOf(
        "Kg",
        "Pound"
    )
    private val goals = arrayOf(
        "Bulk",
        "Maintain",
        "Cut"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){

            val adapterSatuan = ArrayAdapter(
                this@GetStartedActivity,
                R.layout.simple_spinner_item,
                satuanBeratBadan)
            val adapterGoals = ArrayAdapter(
                this@GetStartedActivity,
                R.layout.simple_spinner_item,
                goals)

            adapterSatuan.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            adapterGoals.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)


            spinnerSatuanBerat1.adapter = adapterSatuan
            spinnerSatuanBerat2.adapter = adapterSatuan
            spinnerGoals.adapter = adapterGoals

        }
    }
}