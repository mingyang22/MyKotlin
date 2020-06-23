package com.example.mykotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlin.material.MaterialActivity
import com.example.mykotlin.sunnyweather.ui.MainWeatherActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnName.setOnClickListener { startActivity(Intent(this, MaterialActivity::class.java)) }

        btnWeather.setOnClickListener { startActivity(Intent(this, MainWeatherActivity::class.java)) }

    }


}
