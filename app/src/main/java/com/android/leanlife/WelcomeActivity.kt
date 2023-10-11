package com.android.leanlife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.leanlife.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentToGetStarted = Intent(this@WelcomeActivity, GetStartedActivity::class.java)

        with(binding){
            btnGetStarted.setOnClickListener{
                startActivity(intentToGetStarted)
            }
        }
    }
}