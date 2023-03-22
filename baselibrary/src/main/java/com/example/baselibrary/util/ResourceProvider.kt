package com.example.baselibrary.util

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.baselibrary.R

val resource = ResourceProvider()
const val EMPTY = ""

/**
 * @author ym on 10/1/21
 * 获取资源
 */
@SuppressLint("ResourceType")
class ResourceProvider {

//    private val app = Applications.getCurrent()

    fun getString(@StringRes resId: Int): String {
        if (resId <= 0) return EMPTY
//        return app.getString(resId)
        return ""
    }

    fun getString(@StringRes resId: Int, vararg args: Any?): String {
        if (resId <= 0) return EMPTY
        return String.format(getString(resId), *args)
    }

    fun getColor(@ColorRes resId: Int): Int {
//        return ContextCompat.getColor(app, if (resId <= 0) R.color.cardview_dark_background else resId)
        return 0
    }

    fun getDrawable(@DrawableRes resId: Int): Drawable? {
//        return ContextCompat.getDrawable(app, if (resId <= 0) R.drawable.ui_default_circle_icon else resId)
        return null
    }

    fun getDimension(@DimenRes resId: Int): Float {
        if (resId <= 0) return 0f
//        return app.resources.getDimension(resId)
        return 0f
    }
}