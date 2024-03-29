package com.example.mykotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.example.mykotlin.material.MaterialActivity
import com.example.mykotlin.material.WidgetActivity
import com.example.mykotlin.result.OneActivity
import com.example.mykotlin.sunnyweather.ui.MainWeatherActivity
import com.example.mykotlin.view.ViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnName.setOnClickListener { startActivity(Intent(this, MaterialActivity::class.java)) }
        btnResult.setOnClickListener { startActivity(Intent(this, OneActivity::class.java)) }

        btnWeather.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainWeatherActivity::class.java
                )
            )
        }

        btnMaterial.setOnClickListener { startActivity(Intent(this, WidgetActivity::class.java)) }
        btnMaterial.postDelayed({ Log.e(TAG, "postDelayed: ${lifecycle.currentState}") }, 2000)

        btnView.setOnClickListener { startActivity(Intent(this, ViewActivity::class.java)) }

    }

    override fun onStart() {
        super.onStart()
        Log.e(
            TAG,
            "onStart: ${lifecycle.currentState} ${lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)}"
        )
    }

    override fun onResume() {
        super.onResume()
        Log.e(
            TAG,
            "onResume: ${lifecycle.currentState} ${lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)}"
        )
    }

    override fun onPause() {
        super.onPause()
        Log.e(
            TAG,
            "onPause: ${lifecycle.currentState} ${lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)}"
        )
    }

    override fun onStop() {
        super.onStop()
        Log.e(
            TAG,
            "onStop: ${lifecycle.currentState} ${lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)}"
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e(
            TAG,
            "onSaveInstanceState: ${lifecycle.currentState} ${
                lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
            }"
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e(
            TAG,
            "onRestoreInstanceState: ${lifecycle.currentState} ${
                lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
            }"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(
            TAG,
            "onDestroy: ${lifecycle.currentState} ${lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)}"
        )
    }

}
