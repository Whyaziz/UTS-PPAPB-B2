package com.android.leanlife

import android.R
import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import com.android.leanlife.databinding.ActivityMakananBinding

class MakananActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityMakananBinding

    companion object {
        const val EXTRA_NAME = "foodName"
        const val EXTRA_CALORIES = "calories"
        const val EXTRA_JENIS = "jenis"
        const val EXTRA_TIME = "time"
    }

    lateinit var time : String

    private val jenis = arrayOf(
        "Breakfast",
        "Branch",
        "Lunch",
        "Dinner",
        "Snack"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMakananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){

            val adapterJenis = ArrayAdapter(
                this@MakananActivity,
                R.layout.simple_spinner_item,
                jenis
            )

            adapterJenis.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item
            )

            spinnerJenisMakan.adapter = adapterJenis

            txtTime.setOnClickListener {
                val timePicker = TimePicker()
                timePicker.show(supportFragmentManager,"Time")
            }

            btnSimpan.setOnClickListener{
                val intent = Intent();

                val name = edtNamaMakanan.text.toString()
                val calories = edtJumlahKalori.text.toString()
                val jenis = spinnerJenisMakan.selectedItem.toString()
                val timeSend = time

                if (name.isEmpty() || calories.isEmpty() || jenis.isEmpty() || timeSend.isEmpty()) {
                    Toast.makeText(
                        this@MakananActivity,
                        "Harap isi semua bidang!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    intent.putExtra(EXTRA_NAME, name)
                    intent.putExtra(EXTRA_CALORIES,calories)
                    intent.putExtra(EXTRA_JENIS,jenis)
                    intent.putExtra(EXTRA_TIME,timeSend)

                    setResult(Activity.RESULT_OK, intent)

                    finish()

                }

            }
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val selectedTime = "$hourOfDay:$minute"
        time = selectedTime
    }
}