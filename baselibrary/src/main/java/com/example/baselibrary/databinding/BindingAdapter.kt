package com.example.baselibrary.databinding

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StrikethroughSpan
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.baselibrary.util.resource
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @author ym on 10/1/21
 * databinding 扩展函数，可在xml中直接使用
 * 属性设置和状态设置不要在一个方法中，参考SmartRefreshLayout
 * 更新view状态的属性不要放在一个方法中，会产生影响，特别是会产生互相影响的属性
 */
object BindingAdapter {

    @JvmStatic
    @BindingAdapter("onScrollListener", "onLoadMoreListener", requireAll = false)
    fun RecyclerView.setScrollListeners(
        onScrollListener: (() -> Unit)? = null,
        loadMoreListener: (() -> Unit)? = null
    ) {
        if (onScrollListener == null && loadMoreListener == null) {
            return
        }
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == SCROLL_STATE_IDLE && !canScrollVertically(1)) {
                    loadMoreListener?.invoke()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                onScrollListener?.invoke()
            }
        })
    }

    @JvmStatic
    @BindingAdapter("onScrollListener", "onLoadMoreListener", requireAll = false)
    fun NestedScrollView.setScrollListeners(
        onScrollListener: (() -> Unit)? = null,
        loadMoreListener: (() -> Unit)? = null
    ) {
        if (onScrollListener == null && loadMoreListener == null) {
            return
        }
        setOnScrollChangeListener { _: NestedScrollView?, _: Int, _: Int, _: Int, _: Int ->
            onScrollListener?.invoke()
            if (!canScrollVertically(1)) loadMoreListener?.invoke()
        }
    }

    /**
     * @receiver SmartRefreshLayout
     * @param refreshResult Boolean?
     */
    @JvmStatic
    @BindingAdapter("refreshResult")
    fun SmartRefreshLayout.setOnRefreshResult(refreshResult: Boolean?) {
        if (refreshResult != null) {
            finishRefresh(refreshResult)
            if (refreshResult == true) resetNoMoreData()
        }
    }

    @JvmStatic
    @BindingAdapter("loadMoreResult")
    fun SmartRefreshLayout.setOnLoadMoreResult(loadMoreResult: Boolean?) {
        if (loadMoreResult == true) {
            finishLoadMore()
        } else if (loadMoreResult == false) {
            finishLoadMoreWithNoMoreData()
        }
    }

    @JvmStatic
    @BindingAdapter(
        "refreshHeaderV2",
        "onRefreshListenerV2",
        "onLoadMoreListenerV2",
        requireAll = false
    )
    fun SmartRefreshLayout.setOnRefreshSetting(
        refreshHeaderV2: Int?,
        onRefreshListenerV2: (() -> Unit)?,
        onLoadMoreListenerV2: (() -> Unit)?
    ) {
//        if (refreshHeaderV2 != null && refreshHeaderV2 != 0) {
//            setRefreshHeader(CustomHeader(context, refreshHeaderV2))
//        }
        if (onRefreshListenerV2 != null) setOnRefreshListener { onRefreshListenerV2.invoke() }
        if (onLoadMoreListenerV2 != null) setOnLoadMoreListener { onLoadMoreListenerV2.invoke() }
    }

    /*scroll to top or bottom, must be [FOCUS_UP/FOCUS_DOWN]*/
    @JvmStatic
    @BindingAdapter("scrollByDirection")
    fun NestedScrollView.scrollByDirection(scrollByDirection: Int) {
        fullScroll(scrollByDirection)
    }

    @JvmStatic
    @BindingAdapter("currentItemV2", "smoothScroll", requireAll = false)
    fun ViewPager.switchCurrentItem(current: Int?, smoothScroll: Boolean = false) {
        if (current == null) return

        if (current < (adapter?.count
                ?: 0) && current != currentItem
        ) setCurrentItem(current, smoothScroll)
    }

    @JvmStatic
    @BindingAdapter(
        "imageModel",
        "imageHolder",
        requireAll = false
    )
    fun ImageView.setImageModel(imageModel: Any?, imageHolder: Int? = null) {
        when (imageModel) {
            null -> imageHolder?.let {
                setImageResource(imageHolder)
            }
            is Int -> setImageResource(imageModel)
            else -> {
                var load = Glide.with(context).load(imageModel)

                if (imageHolder != null) {
                    load = load.error(imageHolder)
                }
                load.into(this)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("gifModel", requireAll = false)
    fun ImageView.setGifModel(imageModel: Any?) {
        when (imageModel) {
            is String -> {
                Glide.with(context).asGif().load(imageModel).into(this)
            }
            is Int -> {
                Glide.with(context).asGif().load(imageModel).into(this)
            }
        }
    }


    @JvmStatic
    @BindingAdapter("tintColor")
    fun ImageView.setTintColor(@ColorRes tintColor: Int?) {
        when (tintColor) {
            null -> return
            0 -> clearColorFilter()
            else -> setColorFilter(resource.getColor(tintColor))
        }
    }

    @JvmStatic
    @BindingAdapter("visible", requireAll = false)
    fun View.setVisible(visible: Boolean?) {
        if (visible == true) {
            if (this.visibility != View.VISIBLE) this.visibility = View.VISIBLE
        } else if (visible == false) {
            if (this.visibility != View.GONE) this.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("invisible")
    fun View.setInVisible(invisible: Boolean?) {
        invisible?.let { this.visibility = if (it) View.VISIBLE else View.INVISIBLE }
    }

    /**
     * highLightTexts 添加多个高亮文字
     */
    @JvmStatic
    @BindingAdapter(
        "highLightTexts",
        "highLightTextColor",
        "highLightTextClick",
        requireAll = false
    )
    fun TextView.setHtmlText(
        highLightTexts: List<Any?>?,
        highTextColor: Int,
        highTextClick: ((Int) -> Unit)?
    ) {
        val spannable = SpannableString(text)
        highLightTexts?.forEach {
            val hText = getString(it)
            val startIndex = text.indexOf(hText)
            if (startIndex < 0) return@forEach
            this.highlightColor = Color.TRANSPARENT
            spannable.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    highTextClick?.invoke(highLightTexts.indexOf(it))
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                    ds.color = resource.getColor(highTextColor)
                }
            }, startIndex, startIndex + hText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        text = spannable
        movementMethod = LinkMovementMethod.getInstance()
    }

    /**
     *  添加删除线
     */
    @JvmStatic
    @BindingAdapter("strikeThroughText", requireAll = false)
    fun TextView.setStrikeThroughText(strikeThrough: Boolean?) {
        strikeThrough?.let {
            if (it) {
                val spannable = SpannableString(text)
                val span = StrikethroughSpan()
                spannable.setSpan(span, 0, spannable.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                text = spannable
            }
        }
    }

    /**
     *  设置字体加粗
     */
    @JvmStatic
    @BindingAdapter("boldText")
    fun TextView.setBoldText(boldText: Boolean?) {
        typeface =
            Typeface.create(if (boldText == true) "sans-serif-medium" else null, Typeface.NORMAL)
    }

    /**
     *  设置特殊字体
     */
    @JvmStatic
    @BindingAdapter("fontName")
    fun TextView.setFontPath(fontName: String?) {
        if (fontName.isNullOrEmpty()) return
        typeface = Typeface.createFromAsset(context.assets, "font/$fontName")
    }

    @JvmStatic
    @BindingAdapter("onNormalClick")
    fun View.setNormalClickListener(normalClick: (() -> Unit)?) {
        normalClick?.let {
            setOnClickListener {
                if (System.currentTimeMillis() - ((tag as? Long) ?: 0) > 800) {
                    normalClick()
                    tag = System.currentTimeMillis()
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("onLongClick")
    fun View.setonLongClickListener(onLongClick: (() -> Unit)?) {
        onLongClick?.let {
            setOnLongClickListener {
                onLongClick()
                return@setOnLongClickListener true
            }
        }
    }

    @JvmStatic
    @BindingAdapter("scrollPosition")
    fun RecyclerView.scrollPosition(position: Int?) {
        val layoutManager = layoutManager as? LinearLayoutManager ?: return
        if (position == null) return
        layoutManager.scrollToPositionWithOffset(position, 0)
    }

    @JvmStatic
    @BindingAdapter("showKeyboard")
    fun EditText.setShowKeyboard(showKeyboard: Boolean?) {
        if (showKeyboard == true) {
            requestFocus()
        } else {
            clearFocus()
        }
    }

    private fun getString(text: Any?): String {
        return when (text) {
            null -> ""
            is Int -> resource.getString(text)
            is String -> text
            else -> ""
        }
    }
}