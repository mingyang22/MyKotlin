package com.example.mykotlin.material

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mykotlin.R
import com.google.android.material.shape.AbsoluteCornerSize
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.ShapeAppearanceModel
import kotlinx.android.synthetic.main.activity_widget.*

class WidgetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)
        slider.addOnChangeListener { slider, value, fromUser ->
            // Respond to change in slider's value
        }
        val values = rangeSlider.values

        // 设置边框时需要设置 padding
        imageView?.shapeAppearanceModel = ShapeAppearanceModel.builder()
            /**
             * 设置圆角 CornerFamily 表示处理方式 CUT：直接裁剪圆角部分， ROUNDED：圆角
             * RelativeCornerSize 百分比 0-1， AbsoluteCornerSize 具体数值
             */
//            .setAllCorners(CornerFamily.ROUNDED, resources.getDimension(R.dimen.dp_20))
            .setTopLeftCorner(CornerFamily.CUT, RelativeCornerSize(0.5f))
//            .setTopRightCorner(CornerFamily.CUT, RelativeCornerSize(0.3f))
            .setBottomRightCorner(CornerFamily.CUT, RelativeCornerSize(0.5f))
//            .setBottomLeftCorner(CornerFamily.CUT, RelativeCornerSize(0.3f))
            /**
             * 设置圆形
             */
//            .setAllCornerSizes(ShapeAppearanceModel.PILL)
//            .setTopLeftCornerSize(resources.getDimension(R.dimen.dp_20))
//            .setTopRightCornerSize(RelativeCornerSize(0.3f))
//            .setBottomLeftCornerSize(RelativeCornerSize(0.3f))
//            .setBottomRightCornerSize(AbsoluteCornerSize(30f))
            .build()


    }


}