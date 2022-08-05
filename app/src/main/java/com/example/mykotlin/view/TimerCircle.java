package com.example.mykotlin.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.mykotlin.R;

/**
 * @author ym on 2022/7/22
 * 圆形计时器
 */
public class TimerCircle extends View {
    //圆环画笔
    private Paint mPaint;

    //圆画笔
    private Paint nPaint;

    //1.圆的颜色
    private int circleColor;

    //2.圆环颜色
    private int ringColor;

    //3.圆环大小对应着画笔的宽度
    private int ringWidth;

    //4.指定控件宽度和长度
    private int width;

    private int height;

    //50.字体颜色
    private int textColor;

    //51.字体大小
    private int textSize;

    //52.路径颜色
    private int pathColor;

    //5.通过宽度和高度计算得出圆的半径
    private int radius;

    //6.指定动画的当前值，比如指定动画从0-10000。
    private int currentValue;

    //7.进行到x秒时，对应的圆弧弧度为 x/ * 360.f x可以currentValue得出 currentValue/1000代表秒
    //圆弧角度
    private float angleValue;

    //8.通过valueAnimator我们可以获得currentValue
    private ValueAnimator animator;

    //9.表示valueAnimator的持续时间，这里设置为和最大倒计时时间相同
    private float duration;

    //10.最大倒计时时间
    private int maxTime = 30000;

    //这里设置为10是为了避免 angleValue = currentValue/maxTime*360.0f除数为0的异常，如果既没有在xml中设置最大值就会报错，这是由绘制流程决定的。
    //11.当前的时间是指倒计时剩余时间，需要显示在圆中
    private int currentTime;

    private onFinishListener finishListener;

    public TimerCircle(Context context) {
        this(context, null);
    }

    public TimerCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimerCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimerCircle);

        circleColor = typedArray.getColor(R.styleable.TimerCircle_circleColor, Color.WHITE);
        ringColor = typedArray.getInteger(R.styleable.TimerCircle_ringColor, Color.BLUE);
        ringWidth = (int) typedArray.getDimension(R.styleable.TimerCircle_width, getResources().getDimension(R.dimen.width));
        textColor = typedArray.getColor(R.styleable.TimerCircle_path, Color.RED);
        textSize = (int) typedArray.getDimension(R.styleable.TimerCircle_textSize, getResources().getDimension(R.dimen.textSize));
        pathColor = typedArray.getColor(R.styleable.TimerCircle_path, Color.RED);

        typedArray.recycle();

        initPaint();

    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(ringColor);
        mPaint.setAntiAlias(true);
        nPaint = new Paint();
        nPaint.setAntiAlias(true);
        nPaint.setColor(circleColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthPixels = this.getResources().getDisplayMetrics().widthPixels;
        int heightPixels = this.getResources().getDisplayMetrics().heightPixels;

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int minWidth = Math.min(width, widthPixels);
        int minHeight = Math.min(height, heightPixels);

        setMeasuredDimension(Math.min(minHeight, minWidth), Math.min(minHeight, minWidth));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int in_radius = (this.getWidth() - 2 * ringWidth) / 2;
        int center = this.getWidth() - in_radius - ringWidth;
        drawInner(canvas, in_radius, center);
        drawText(canvas, center);
    }

    private void drawInner(Canvas canvas, int radius, int center) {
//        canvas.drawCircle(center, center, radius, nPaint);
        //25.画圆环，设置为空心圆，指定半径为内圆的半径，画笔的宽度就是圆环的宽度
        //26.设置空心圆
        mPaint.setStyle(Paint.Style.STROKE);

        //27.画笔宽度即圆环宽度
        mPaint.setStrokeWidth(ringWidth);

        //28. 圆环的颜色
        mPaint.setColor(ringColor);

//        canvas.drawCircle(center, center, radius, mPaint);

        //30.内圆的外接矩形，有什么作用？绘制圆弧时根据外接矩形绘制
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);

        //31.计算弧度，通过当前的currentValue的值得到
        angleValue = currentValue * 360.0f / maxTime;

        //32.设置阴影大小和颜色
//        mPaint.setShadowLayer(10, 10, 10, Color.BLUE);

        //33.指定线帽样式，可以理解为一条有宽度的直线的两端是带有弧度的
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        //34.圆弧的颜色
        mPaint.setColor(pathColor);

        //35.绘制圆弧
        canvas.drawArc(rectF, -90, angleValue, false, mPaint);

    }

    public void setDuration(int duration, final int maxTime) {
        //36.如果外部指定了最大倒计时的时间，则xml定义的最大倒计时无效，以外部设置的为准
        this.maxTime = maxTime;
        //37.持续时间和最大时间保持一致，方便计算
        this.duration = duration;
        if (animator != null) {
            animator.cancel();
        } else {
            animator = ValueAnimator.ofInt(0, maxTime);
            animator.addUpdateListener(animation -> {
                currentValue = (int) animation.getAnimatedValue();
                if (currentValue == maxTime) {
                    Log.e("TAG", "animator: " + currentValue / 1000);
                    if (finishListener != null) {
                        finishListener.onFinish();
                    }
                }
                invalidate();
            });

            //40.线性插值器，匀速变化
            animator.setInterpolator(new LinearInterpolator());
        }

        animator.setDuration(duration);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);

    }

    public void setCurrentValue(int second) {
        if (second < maxTime) {
            if (animator != null) {
                animator.setCurrentPlayTime(second);
            }
        }
    }

    public void start() {
        if (animator != null) {
            if (animator.isPaused()) {
                animator.resume();
            } else {
                animator.start();
            }
        }
    }

    public void stop() {
        if (animator != null) {
            animator.pause();
        }
    }

    private void drawText(Canvas canvas, int center) {
        currentTime = (maxTime - currentValue) / 1000;
        String Text;
        if (currentTime < 10) {
            Text = "00:0" + currentTime;
        } else if (currentTime <= 60) {
            Text = "00:" + currentTime;
        } else if (currentTime < 600) {
            int min = currentTime / 60;
            int sen = currentTime % 60;
            if (sen < 10) {
                Text = "0" + min + ":0" + sen;
            } else {
                Text = "0" + min + ":" + sen;
            }
        } else {
            int min = currentTime / 60;
            int sen = currentTime % 60;
            if (sen < 10) {
                Text = min + ":0" + sen;
            } else {
                Text = min + ":" + sen;
            }
        }

        mPaint.setTextAlign(Paint.Align.CENTER);

        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.setStrokeWidth(0);
        mPaint.clearShadowLayer();

        Rect bounds = new Rect();

        mPaint.getTextBounds(Text, 0, Text.length(), bounds);

        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();

        int baseline = center - (fontMetrics.bottom + fontMetrics.top) / 2;

        canvas.drawText(Text, center, baseline, mPaint);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
    }

    public interface onFinishListener {
        void onFinish();
    }

    public void setFinishListener(onFinishListener listener) {
        this.finishListener = listener;
    }

}
