package com.example.baselibrary.recycleview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author yang on 2020/5/10
 */
class ViewHolder(private val context: Context, private val mConvertView: View) :
    RecyclerView.ViewHolder(mConvertView) {

    private var mViews: SparseArray<View> = SparseArray()

    /**
     * 静态方法
     */
    companion object {
        fun createViewHolder(context: Context, itemView: View): ViewHolder =
            ViewHolder(context, itemView)

        fun createViewHolder(context: Context, parent: ViewGroup, layoutId: Int): ViewHolder {
            val itemView = LayoutInflater.from(context).inflate(layoutId, parent, false)
            return ViewHolder(context, itemView)
        }
    }

    fun getView(viewId: Int): View {
        var view = mViews[viewId]
        if (view == null) {
            view = mConvertView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view
    }

    fun getConvertView() = mConvertView

    fun setText(viewId: Int, text: String) = ViewHolder.apply {
        val view = getView(viewId) as TextView
        view.text = text
    }

    fun setTextColor(viewId: Int, textColor: Int) = ViewHolder.apply {
        val view = getView(viewId) as TextView
        view.setTextColor(textColor)
    }

    fun setTextColorRes(viewId: Int, textColorRes: Int) = ViewHolder.apply {
        val view = getView(viewId) as TextView
        view.setTextColor(context.resources.getColor(textColorRes))
    }

    fun setImageResource(viewId: Int, resId: Int) = ViewHolder.apply {
        val view = getView(viewId) as ImageView
        view.setImageResource(resId)
    }

    fun setImageBitmap(viewId: Int, bitmap: Bitmap) = ViewHolder.apply {
        val view = getView(viewId) as ImageView
        view.setImageBitmap(bitmap)
    }

    fun setImageDrawable(viewId: Int, drawable: Drawable) = ViewHolder.apply {
        val view = getView(viewId) as ImageView
        view.setImageDrawable(drawable)
    }

    fun setBackgroundColor(viewId: Int, color: Int) = ViewHolder.apply {
        val view = getView(viewId)
        view.setBackgroundColor(color)
    }

    fun setBackgroundRes(viewId: Int, backgroundRes: Int) = ViewHolder.apply {
        val view = getView(viewId)
        view.setBackgroundResource(backgroundRes)
    }

    fun setVisible(viewId: Int, visible: Boolean) = ViewHolder.apply {
        val view = getView(viewId)
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun setVisibleForInvisible(viewId: Int, visible: Boolean) = ViewHolder.apply {
        val view = getView(viewId)
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    fun setOnClickListener(viewId: Int, listener: View.OnClickListener) {
        val view = getView(viewId)
        view.setOnClickListener(listener)
    }

    fun setOnLongClickListener(viewId: Int, listener: View.OnLongClickListener) {
        val view = getView(viewId)
        view.setOnLongClickListener(listener)
    }

}