package com.example.mykotlin.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import java.util.*
import kotlin.math.acos
import kotlin.math.cos
import kotlin.system.measureTimeMillis

/**
 * @author ym on 3/1/21
 * 粒子波纹动效
 */
class DimpleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    // 粒子集合
    private var particleList = mutableListOf<Particle>()

    // 画笔
    var paint = Paint()

    // 圆形路径
    var path = Path()

    var centerX: Float = 0.0f
    var centerY: Float = 0.0f

    val random = Random()

    // 路径，用于测量扩散圆某一处的X,Y值
    private val pathMeasure = PathMeasure()

    // 扩散圆上某一点的x,y
    private var pos = FloatArray(2)

    // 扩散圆上某一点切线
    private var tan = FloatArray(2)


    private var animator = ValueAnimator.ofFloat(0f, 1f)

    init {
        animator.duration = 2000
        animator.repeatCount = Animation.INFINITE
        // 匀速插值器
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            updateParticle(it.animatedValue as Float)
            // 重绘界面
            invalidate()
        }
    }

    private fun updateParticle(value: Float) {
        particleList.forEach {
            if (it.offset > it.maxOffset) {
                it.offset = 0
                it.speed = (random.nextInt(10) + 5).toFloat()
            }
            it.alpha = (1f - (it.offset / it.maxOffset) * 255f).toInt()
            it.x = (centerX + cos(it.angle) * (280f + it.offset)).toFloat()
            it.y += it.speed
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()

        path.addCircle(centerX, centerY, 280f, Path.Direction.CCW)
        pathMeasure.setPath(path, false)

        var nextX = 0f
        var nextY = 0f
        var speed = 0
        var angle = 0.0
        for (i in 0..500) {
            // 按比例测量路径上每一点的值
            pathMeasure.getPosTan(i / 500f * pathMeasure.length, pos, tan)
            nextX = pos[0] + random.nextInt(6) - 3f
            nextY = pos[1] + random.nextInt(6) - 3f
            // 初始速度从5-15不等
            speed = random.nextInt(10) + 5
            // 反余弦函数得到角度值，是弧度
            angle = acos(((pos[0] - centerX) / 280f).toDouble())
            particleList.add(Particle(nextX, nextY, 2f, speed.toFloat(), 100, 300f, 0, angle))
        }
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.WHITE
        // 抗锯齿
        paint.isAntiAlias = true
        var time = measureTimeMillis {
            particleList.forEach {
                // 设置画笔透明度
                paint.alpha = it.alpha
                canvas.drawCircle(it.x, it.y, it.radius, paint)
            }
        }
//        Log.i("Dimple", "绘制时间$time ms")
    }

}