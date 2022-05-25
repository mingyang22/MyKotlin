package com.example.mykotlin.result

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.example.mykotlin.R
import kotlinx.android.synthetic.main.activity_one.*

/**
 * 测试结果标明  使用ActivityResult API跳转页面B，在B页面跳转C并finish自身，此时A不会收到回调
 */
class OneActivity : AppCompatActivity() {

    companion object {
        const val TAG = "OneActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        val launcher = createActivityResultLauncher()
        tv.setOnClickListener {
            val intent = Intent(this, TwoActivity::class.java)
            launcher.launch(intent)
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

    private fun createActivityResultLauncher(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                Log.e(TAG, "createActivityResultLauncher: finish" )
                finish()
            }
        }
    }

}
