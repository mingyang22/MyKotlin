package com.example.baselibrary.recycleview

import android.content.Context

/**
 * @author yang on 2020/5/10
 */
abstract class CommonAdapter<T>(
    context: Context, private val layoutId: Int, list: ArrayList<T> = ArrayList()
) :
    MultiItemTypeAdapter<T>(context, list) {

    init {
        addItemViewDelegate(object : ItemViewDelegate<T> {

            override fun getItemViewLayoutId(): Int {
                return layoutId
            }

            override fun isForViewType(item: T, position: Int): Boolean {
                return true
            }

            override fun convert(holder: ViewHolder, t: T, position: Int) {
                this@CommonAdapter.convert(holder, t, position)
            }

            override fun convert(holder: ViewHolder, t: T, position: Int, payloads: List<Any>) {
            }
        })
    }

    abstract fun convert(holder: ViewHolder, data: T, position: Int)

}