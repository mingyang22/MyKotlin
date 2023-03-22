package com.basestonedata.basecomponent.databinding

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.baselibrary.R
import com.example.baselibrary.util.resource

/**
 * @author ym on 10/1/21
 * 设置view 背景
 */
object ViewShapeAdapter {

    @JvmStatic
    @BindingAdapter(
        "bgRadius",
        "bgTopLeftRadius",
        "bgTopRightRadius",
        "bgBottomLeftRadius",
        "bgBottomRightRadius",
        "bgSolidColor",
        "bgRippleColor",
        "bgStrokeWidth",
        "bgStrokeColor",
        "bgGradientStartColor",
        "bgGradientEndColor",
        "bgGradientOrientation",
        "bgShapeWidth",
        "bgShapeHeight",
        "bgEnable",
        requireAll = false
    )
    fun View.setBgParams(
        bgRadius: Int? = null,
        bgTopLeftRadius: Int? = null,
        bgTopRightRadius: Int? = null,
        bgBottomLeftRadius: Int? = null,
        bgBottomRightRadius: Int? = null,
        bgSolidColor: Int? = null,
        bgRippleColor: Int? = null,
        bgStrokeWidth: Int? = null,
        bgStrokeColor: Int? = null,
        bgGradientStartColor: Int? = null,
        bgGradientEndColor: Int? = null,
        bgGradientOrientation: GradientDrawable.Orientation? = null,
        bgShapeWidth: Int? = null,
        bgShapeHeight: Int? = null,
        bgEnable: Boolean? = null
    ) {

        if (isEffective(bgRippleColor)) {
            setRippleBg(this, bgRippleColor, bgSolidColor)
            return
        }

        val drawable = when {
            isEffective(bgGradientStartColor) && isEffective(bgGradientEndColor) -> GradientDrawable(
                bgGradientOrientation, intArrayOf(
                    ContextCompat.getColor(context, bgGradientStartColor!!), ContextCompat.getColor(context, bgGradientEndColor!!)
                )
            )
            else -> GradientDrawable().apply {
                if (isEffective(bgSolidColor)) setColor(ContextCompat.getColor(context, bgSolidColor!!))
            }
        }

        if (isEffective(bgStrokeWidth) && isEffective(bgStrokeColor)) {
            drawable.setStroke(
                resources.getDimension(bgStrokeWidth!!).toInt(),
                ContextCompat.getColor(context, bgStrokeColor!!)
            )
        }

        bgEnable?.let {
            isEnabled = it
            drawable.alpha = if (it) 225 else 68
        }

        if (bgRadius != null) {
            drawable.cornerRadius = resources.getDimension(bgRadius)
        } else if ((bgTopLeftRadius != null || bgTopRightRadius != null) || bgBottomLeftRadius != null || bgBottomRightRadius != null) {
            val topLeft = bgTopLeftRadius?.let { resources.getDimension(bgTopLeftRadius) } ?: 0f
            val topRight = bgTopRightRadius?.let { resources.getDimension(bgTopRightRadius) } ?: 0f
            val bottomLeft = bgBottomLeftRadius?.let { resources.getDimension(bgBottomLeftRadius) } ?: 0f
            val bottomRight = bgBottomRightRadius?.let { resources.getDimension(bgBottomRightRadius) } ?: 0f

            drawable.cornerRadii =
                floatArrayOf(topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft)
        } else {
            drawable.cornerRadius = resources.getDimension(R.dimen.cardview_compat_inset_shadow)
        }

        if (isEffective(bgShapeWidth) && isEffective(bgShapeHeight)) {
            drawable.setSize(
                resources.getDimension(bgShapeWidth!!).toInt(), resources.getDimension(bgShapeHeight!!).toInt()
            )
        }

        background = drawable
    }

    private fun setRippleBg(view: View, bgRippleColor: Int?, bgSolidColor: Int?) {
        if (bgRippleColor == null) return

        val pressedColor = resource.getColor(bgRippleColor)
        val normalColor = if (bgSolidColor != null) resource.getColor(bgSolidColor) else Color.TRANSPARENT

        val outRadius = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
        val roundRectShape = RoundRectShape(outRadius, null, null)

        val maskDrawable = ShapeDrawable()
        maskDrawable.shape = roundRectShape
        maskDrawable.paint.style = Paint.Style.FILL

        val contentDrawable = ShapeDrawable()
        contentDrawable.shape = roundRectShape
        contentDrawable.paint.color = normalColor
        contentDrawable.paint.style = Paint.Style.FILL

        val colorStateList = ColorStateList.valueOf(pressedColor)
        val rippleDrawable = RippleDrawable(colorStateList, contentDrawable, maskDrawable)
        view.background = rippleDrawable
    }


    @JvmStatic
    @BindingAdapter(
        "normalImg",
        "pressImg"
    )
    fun View.setStateBackgroundOrDrawable(normalImg: Int?, pressImg: Int?) {
        if (normalImg == null || pressImg == null) {
            return
        }
        val drawable = StateListDrawable()
        drawable.addState(intArrayOf(android.R.attr.state_pressed), resource.getDrawable(pressImg))
        drawable.addState(intArrayOf(-android.R.attr.state_checked), resource.getDrawable(normalImg))
        background = drawable
    }

    private fun isEffective(resId: Any?): Boolean {
        return resId != null && resId != 0
    }
}