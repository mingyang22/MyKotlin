package com.example.mykotlin.result

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.example.mykotlin.R
import kotlinx.android.synthetic.main.activity_two.*

class TwoActivity : AppCompatActivity() {

    companion object {
        const val TAG = "TwoActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        tv.setOnClickListener {
            startActivity(Intent(this, ThreeActivity::class.java))
            setResult(RESULT_OK)
            finish()
        }

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
