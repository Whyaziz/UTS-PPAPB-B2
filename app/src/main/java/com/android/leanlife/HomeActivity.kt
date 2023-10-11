package com.android.leanlife

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.android.leanlife.databinding.ActivityHomeBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    lateinit var kaloriSisa : PieChart
    var kaloriMasuk = 0
    var kaloriKeluar = 0

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            //TODO : add function callback after next page is dismissed
            //Update txtName dengan alamat dari ThirdActivity
                result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data

                val name = data?.getStringExtra(MakananActivity.EXTRA_NAME)
                val kalori = data?.getStringExtra(MakananActivity.EXTRA_CALORIES)
                val jenis = data?.getStringExtra(MakananActivity.EXTRA_JENIS)
                val time = data?.getStringExtra(MakananActivity.EXTRA_TIME)

                binding.existTxt.text ="$name $kalori $jenis $time"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val targetKalori = intent.getIntExtra(GetStartedActivity.EXTRA_CALORIES,0)

        with(binding){
            kaloriSisa = chartKaloriSisa

            txtTargetKalori.text = targetKalori.toString()
            txtKaloriMasuk.text = kaloriMasuk.toString()
            txtKaloriKeluar.text = kaloriKeluar.toString()

            val list : ArrayList<PieEntry> = arrayListOf()

            list.add(PieEntry(targetKalori.toFloat(),targetKalori.toString()))
            list.add(PieEntry(kaloriMasuk.toFloat(),kaloriMasuk.toString()))

            val pieDataSet = PieDataSet(list,"")
            pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
            pieDataSet.valueTextSize = 0f

            val pieData = PieData(pieDataSet)

            kaloriSisa.data = pieData
            kaloriSisa.description.text = ""
            kaloriSisa.centerText = "Kalori Sisa"
            kaloriSisa.animateY(2000)

            btnPluss.setOnClickListener{
                val intent = Intent(this@HomeActivity, MakananActivity::class.java)
                launcher.launch(intent)
            }
        }
    }
}