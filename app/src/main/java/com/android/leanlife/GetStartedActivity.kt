package com.android.leanlife

import android.R
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.leanlife.databinding.ActivityGetStartedBinding

class GetStartedActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

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

    companion object {
        const val EXTRA_NAME = "name"
        const val EXTRA_CURRENT_WEIGHT = "cWeight"
        const val EXTRA_CURRENT_WEIGHT_SATUAN = "cWeightSatuan"
        const val EXTRA_TARGET_WEIGHT = "tWeight"
        const val EXTRA_TARGET_WEIGHT_SATUAN = "tWeightSatuan"
        const val EXTRA_CALORIES = "calories"
        const val EXTRA_GOALS = "goals"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            val adapterSatuan = ArrayAdapter(
                this@GetStartedActivity,
                R.layout.simple_spinner_item,
                satuanBeratBadan
            )
            val adapterGoals = ArrayAdapter(
                this@GetStartedActivity,
                R.layout.simple_spinner_item,
                goals
            )

            adapterSatuan.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item
            )
            adapterGoals.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item
            )


            spinnerSatuanBerat1.adapter = adapterSatuan
            spinnerSatuanBerat2.adapter = adapterSatuan
            spinnerGoals.adapter = adapterGoals


            val intentToHome = Intent(this@GetStartedActivity, HomeActivity::class.java)

            txtCalendar.setOnClickListener {
                val calendar = DatePicker()
                calendar.show(supportFragmentManager, "Calendar")
            }

            btnMulai.setOnClickListener {
                val name = edtNama.text.toString()
                val cWeightText = edtBeratSekarang.text.toString()
                val tWeightText = edtBeratTujuan.text.toString()
                val caloriesText = edtKalori.text.toString()

                if (name.isEmpty() || cWeightText.isEmpty() || tWeightText.isEmpty() || caloriesText.isEmpty()) {
                    Toast.makeText(
                        this@GetStartedActivity,
                        "Harap isi semua bidang!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val cWeight = cWeightText.toInt()
                    val cWeightSatuan = spinnerSatuanBerat1.selectedItem.toString()
                    val tWeight = tWeightText.toInt()
                    val tWeightSatuan = spinnerSatuanBerat2.selectedItem.toString()
                    val calories = caloriesText.toInt()
                    val goals = spinnerGoals.selectedItem.toString()

                    intentToHome.putExtra(EXTRA_NAME, name)
                    intentToHome.putExtra(EXTRA_CURRENT_WEIGHT, cWeight)
                    intentToHome.putExtra(EXTRA_CURRENT_WEIGHT_SATUAN, cWeightSatuan)
                    intentToHome.putExtra(EXTRA_TARGET_WEIGHT, tWeight)
                    intentToHome.putExtra(EXTRA_TARGET_WEIGHT_SATUAN, tWeightSatuan)
                    intentToHome.putExtra(EXTRA_CALORIES, calories)
                    intentToHome.putExtra(EXTRA_GOALS, goals)

                    startActivity(intentToHome)
                }
            }
        }
    }

    override fun onDateSet(
        view: android.widget.DatePicker?,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val selectedDAte = "$dayOfMonth/${month+1}/$year"
        binding.txtCalendar.text = selectedDAte
    }
}