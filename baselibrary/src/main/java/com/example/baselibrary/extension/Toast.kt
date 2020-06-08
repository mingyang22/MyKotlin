package com.example.baselibrary.extension

import android.content.Context
import android.widget.Toast

/**
 * @author yang on 2020/5/22
 * Toast 扩展函数
 */

fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun Int.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}