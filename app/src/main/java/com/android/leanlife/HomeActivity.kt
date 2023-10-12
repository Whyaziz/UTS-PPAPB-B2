package com.android.leanlife

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import com.android.leanlife.databinding.ActivityHomeBinding
import com.android.leanlife.databinding.ActivityOlahragaBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class HomeActivity : AppCompatActivity() {

    fun updatePieChart() {
        val targetKalori = intent.getIntExtra(GetStartedActivity.EXTRA_CALORIES, 0)

        val list: ArrayList<PieEntry> = arrayListOf(
            PieEntry(targetKalori.toFloat()-kaloriMasuk, "sisa"),
            PieEntry((kaloriMasuk-kaloriKeluar).toFloat(), "")
        )

        val pieDataSet = PieDataSet(list, "")
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
        pieDataSet.valueTextSize = 0f

        val pieData = PieData(pieDataSet)

        kaloriSisa.data = pieData
        kaloriSisa.description.text = ""
        kaloriSisa.centerText = "Kalori Sisa"
        kaloriSisa.animateY(2000)
    }

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
                kaloriMasuk += kalori.toString().toInt()
                binding.txtKaloriMasuk.text = kaloriMasuk.toString()


                updatePieChart()
            }
            if (result.resultCode == 13) {
                val data = result.data

                val name = data?.getStringExtra(OlahragaActivity.EXTRA_NAME).toString()
                val kalori = data?.getStringExtra(OlahragaActivity.EXTRA_CALORIES).toString()
                val durasi = data?.getStringExtra(OlahragaActivity.EXTRA_DURATION).toString()
                val time = data?.getStringExtra(OlahragaActivity.EXTRA_TIME).toString()

                binding.presentTxt.text ="$name $kalori $durasi $time"
                kaloriKeluar += kalori.toString().toInt()
                binding.txtKaloriKeluar.text = kaloriKeluar.toString()

                updatePieChart()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val targetKalori = intent.getIntExtra(GetStartedActivity.EXTRA_CALORIES,0)

        with(binding){
            btnPluss.setOnClickListener{
                val intent = Intent(this@HomeActivity, MakananActivity::class.java)
                launcher.launch(intent)
            }
            btnAdd.setOnClickListener{
                val intent = Intent(this@HomeActivity, OlahragaActivity::class.java)
                launcher.launch(intent)
            }

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
        }

    }
}