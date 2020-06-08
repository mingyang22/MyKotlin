package com.example.baselibrary.recycleview

/**
 * @author yang on 2020/5/10
 */
interface ItemViewDelegate<T> {
    fun getItemViewLayoutId(): Int
    fun isForViewType(item: T, position: Int): Boolean
    fun convert(holder: ViewHolder, t: T, position: Int)
    fun convert(holder: ViewHolder, t: T, position: Int, payloads: List<Any>)

}