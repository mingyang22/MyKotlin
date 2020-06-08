package com.example.baselibrary.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * @author yang on 2020/5/22
 */
fun View.showSnackBar(
    text: String, actionText: String? = null,
    duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null
) {
    val snackBar = Snackbar.make(this, text, duration)
    if (actionText != null && block != null) {
        snackBar.setAction(actionText) {
            block()
        }
    }
    snackBar.show()
}

fun View.showSnackBar(
    resId: Int, actionResId: Int? = null,
    duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null
) {
    val snackBar = Snackbar.make(this, resId, duration)
    if (actionResId != null && block != null) {
        snackBar.setAction(actionResId) {
            block()
        }
    }
    snackBar.show()
}